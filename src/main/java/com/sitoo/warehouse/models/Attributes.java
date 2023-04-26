package com.sitoo.warehouse.models;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name="attributes")
public class Attributes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attribute_id")
    private long attributeId;
    private String name;
    private String value;

    @ManyToOne(fetch = FetchType.LAZY)
    private Products products;

    public Attributes(long attributeId, String name, String value) {
        this.attributeId = attributeId;
        this.name = name;
        this.value = value;
    }

    public Attributes() {
    }

    public long getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(long attributeId) {
        this.attributeId = attributeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Attributes attribute = (Attributes) o;
        return attributeId == attribute.attributeId && attribute.value == value && Objects.equals(name, attribute.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(attributeId, name, value);
    }

    @Override
    public String toString() {
        return "Attribute{" +
                "attributeId=" + attributeId +
                ", name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
