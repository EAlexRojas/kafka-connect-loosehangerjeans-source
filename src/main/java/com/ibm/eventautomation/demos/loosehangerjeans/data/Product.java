/**
 * Copyright 2023 IBM Corp. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.ibm.eventautomation.demos.loosehangerjeans.data;

import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.data.SchemaBuilder;
import org.apache.kafka.connect.data.Struct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// TODO Documentation
public class Product {

    private static final Logger LOG = LoggerFactory.getLogger(Product.class);

    /** The size of the product. */
    private final String size;

    /** The material of the product. */
    private final String material;

    /** The style of the product. */
    private final String style;

    /** The name of the product. */
    private final String name;

    // TODO Documentation
    public static final Schema SCHEMA = SchemaBuilder.struct()
            .name("product")
            .version(1)
            .field("size",          Schema.STRING_SCHEMA)
            .field("material",      Schema.STRING_SCHEMA)
            .field("style",         Schema.STRING_SCHEMA)
            .field("name",          Schema.STRING_SCHEMA)
            .build();

    /** Creates a product from the provided description.
     * NULL is returned if the product cannot be created from the provided description.
     */
    public static Product parseDescription(String description) {
        String[] descriptionParts = description.split("\s");
        if (descriptionParts.length != 4) {
            LOG.error("Impossible to create a product from the description: " + description);
            return null;
        } else {
            return new Product(descriptionParts[0],
                    descriptionParts[1],
                    descriptionParts[2],
                    descriptionParts[3]);
        }
    }

    // TODO Documentation
    public Product(String size, String material, String style, String name) {
        this.size = size;
        this.material = material;
        this.style = style;
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public String getMaterial() {
        return material;
    }

    public String getStyle() {
        return style;
    }

    public String getName() {
        return name;
    }

    // TODO Documentation
    public String getDescription() {
        return getSize() + " " +
            getMaterial() + " " +
            getStyle() + " " +
            getName();
    }

    // TODO Documentation
    public Struct toStruct() {
        Struct struct = new Struct(SCHEMA);
        struct.put(SCHEMA.field("size"),        size);
        struct.put(SCHEMA.field("material"),    material);
        struct.put(SCHEMA.field("style"),       style);
        struct.put(SCHEMA.field("name"),        name);
        return struct;
    }

    @Override
    public String toString() {
        return "Product [size=" + size + ", material=" + material
                + ", style= " + style + ", name=" + name + "]";
    }
}
