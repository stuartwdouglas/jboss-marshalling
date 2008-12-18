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

/**
 *
 */
public final class Protocol {
    public static final int ID_NULL_OBJECT              = 0x01;
    public static final int ID_REPEAT_OBJECT            = 0x02;
    public static final int ID_PREDEFINED_OBJECT        = 0x03;
    public static final int ID_NEW_OBJECT               = 0x04;
    public static final int ID_NEW_OBJECT_UNSHARED      = 0x05;

    public static final int ID_REPEAT_CLASS             = 0x06;

    public static final int ID_PLAIN_CLASS              = 0x07;
    public static final int ID_PROXY_CLASS              = 0x08; // Proxy.isProxy(?) == true
    public static final int ID_SERIALIZABLE_CLASS       = 0x09; // ? extends Serializable.class
    public static final int ID_EXTERNALIZABLE_CLASS     = 0x0a; // ? extends Externalizable.class
    public static final int ID_EXTERNALIZER_CLASS       = 0x0b;
    public static final int ID_ENUM_TYPE_CLASS          = 0x0c; // ? extends Enum.class
    public static final int ID_OBJECT_ARRAY_TYPE_CLASS  = 0x0d; // ? extends Object[].class

    public static final int ID_PREDEFINED_PLAIN_CLASS               = 0x0e;
    public static final int ID_PREDEFINED_PROXY_CLASS               = 0x0f;
    public static final int ID_PREDEFINED_SERIALIZABLE_CLASS        = 0x10;
    public static final int ID_PREDEFINED_EXTERNALIZABLE_CLASS      = 0x11;
    public static final int ID_PREDEFINED_EXTERNALIZER_CLASS        = 0x12;
    public static final int ID_PREDEFINED_ENUM_TYPE_CLASS           = 0x13;

    // the following are never cached
    public static final int ID_STRING_CLASS             = 0x14; // String.class
    public static final int ID_CLASS_CLASS              = 0x15; // Class.class
    public static final int ID_OBJECT_CLASS             = 0x16; // Object.class
    public static final int ID_ENUM_CLASS               = 0x17; // Enum.class

    public static final int ID_BOOLEAN_ARRAY_CLASS      = 0x18; // boolean[].class
    public static final int ID_BYTE_ARRAY_CLASS         = 0x19; // ..etc..
    public static final int ID_SHORT_ARRAY_CLASS        = 0x1a;
    public static final int ID_INT_ARRAY_CLASS          = 0x1b;
    public static final int ID_LONG_ARRAY_CLASS         = 0x1c;
    public static final int ID_CHAR_ARRAY_CLASS         = 0x1d;
    public static final int ID_FLOAT_ARRAY_CLASS        = 0x1e;
    public static final int ID_DOUBLE_ARRAY_CLASS       = 0x1f;

    public static final int ID_PRIM_BOOLEAN             = 0x20; // boolean.class
    public static final int ID_PRIM_BYTE                = 0x21; // ..etc..
    public static final int ID_PRIM_SHORT               = 0x22;
    public static final int ID_PRIM_INT                 = 0x23;
    public static final int ID_PRIM_LONG                = 0x24;
    public static final int ID_PRIM_CHAR                = 0x25;
    public static final int ID_PRIM_FLOAT               = 0x26;
    public static final int ID_PRIM_DOUBLE              = 0x27;

    public static final int ID_VOID                     = 0x28; // void.class

    public static final int ID_BOOLEAN_CLASS            = 0x29; // Boolean.class
    public static final int ID_BYTE_CLASS               = 0x2a; // ..etc..
    public static final int ID_SHORT_CLASS              = 0x2b;
    public static final int ID_INTEGER_CLASS            = 0x2c;
    public static final int ID_LONG_CLASS               = 0x2d;
    public static final int ID_CHARACTER_CLASS          = 0x2e;
    public static final int ID_FLOAT_CLASS              = 0x2f;
    public static final int ID_DOUBLE_CLASS             = 0x30;

    public static final int ID_VOID_CLASS               = 0x31; // Void.class

    private Protocol() {
    }
}
