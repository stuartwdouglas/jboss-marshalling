/*
 * JBoss, Home of Professional Open Source
 * Copyright 2008, JBoss Inc., and individual contributors as indicated
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.jboss.river;

import java.util.Arrays;

/**
 *
 */
public final class IdentityIntMap<T> {
    private int[] values;
    private Object[] keys;
    private int count;
    private int resizeCount;

    public IdentityIntMap(int initialCapacity, final float loadFactor) {
        if (initialCapacity < 1) {
            throw new IllegalArgumentException("initialCapacity must be > 0");
        }
        if (loadFactor <= 0.0f || loadFactor >= 1.0f) {
            throw new IllegalArgumentException("loadFactor must be > 0.0 and < 1.0");
        }
        if (initialCapacity < 16) {
            initialCapacity = 16;
        } else {
            // round up
            final int c = Integer.highestOneBit(initialCapacity) - 1;
            initialCapacity = Integer.highestOneBit(initialCapacity + c);
        }
        keys = new Object[initialCapacity];
        values = new int[initialCapacity];
        resizeCount = (int) ((double) initialCapacity * (double) loadFactor);
    }

    public IdentityIntMap(final float loadFactor) {
        this(64, loadFactor);
    }

    public IdentityIntMap(final int initialCapacity) {
        this(initialCapacity, 0.5f);
    }

    public IdentityIntMap() {
        this(0.5f);
    }

    public int get(T key, int defVal) {
        if (key == null) {
            throw new NullPointerException("key is null");
        }
        final Object[] keys = this.keys;
        final int mask = keys.length - 1;
        int hc = System.identityHashCode(key) & mask;
        Object v;
        for (;;) {
            v = keys[hc];
            if (v == key) {
                return values[hc];
            }
            if (v == null) {
                // not found
                return defVal;
            }
            hc = (hc + 1) & mask;
        }
    }

    public void put(T key, int value) {
        if (key == null) {
            throw new NullPointerException("key is null");
        }
        final Object[] keys = this.keys;
        final int mask = keys.length - 1;
        final int[] values = this.values;
        Object v;
        int hc = System.identityHashCode(key) & mask;
        for (int idx = hc;; idx = hc++ & mask) {
            v = keys[idx];
            if (v == null) {
                keys[idx] = key;
                values[idx] = value;
                if (++count > resizeCount) {
                    resize();
                }
                return;
            }
            if (v == key) {
                values[idx] = value;
                return;
            }
        }
    }

    private final void resize() {
        final Object[] oldKeys = keys;
        final int oldsize = oldKeys.length;
        final int[] oldValues = values;
        if (oldsize >= 0x40000000) {
            throw new IllegalStateException("Table full");
        }
        final int newsize = oldsize << 1;
        final int mask = newsize - 1;
        final Object[] newKeys = new Object[newsize];
        final int[] newValues = new int[newsize];
        keys = newKeys;
        values = newValues;
        if ((resizeCount <<= 1) == 0) {
            resizeCount = Integer.MAX_VALUE;
        }
        for (int oi = 0; oi < oldsize; oi ++) {
            final Object key = oldKeys[oi];
            if (key != null) {
                int ni = System.identityHashCode(key) & mask;
                for (;;) {
                    final Object v = newKeys[ni];
                    if (v == null) {
                        // found
                        newKeys[ni] = key;
                        newValues[ni] = oldValues[oi];
                        break;
                    }
                    ni = (ni + 1) & mask;
                }
            }
        }
    }

    public void clear() {
        Arrays.fill(keys, null);
        count = 0;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Map length = ").append(keys.length).append(", count = ").append(count).append(", resize count = ").append(resizeCount).append('\n');
        for (int i = 0; i < keys.length; i ++) {
            builder.append('[').append(i).append("] = ");
            if (keys[i] != null) {
                final int hc = System.identityHashCode(keys[i]);
                builder.append("{ ").append(keys[i]).append(" (hash ").append(hc).append(", modulus ").append(hc % keys.length).append(") => ").append(values[i]).append(" }");
            } else {
                builder.append("(blank)");
            }
            builder.append('\n');
        }
        return builder.toString();
    }
}
