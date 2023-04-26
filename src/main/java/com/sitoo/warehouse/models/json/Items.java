package com.sitoo.warehouse.models.json;

import java.util.Objects;

public class Items {
    private long productId;
    private String title;

    public Items(long productId, String title) {
        this.productId = productId;
        this.title = title;
    }

    public Items() {
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Items items = (Items) o;
        return productId == items.productId && Objects.equals(title, items.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, title);
    }

    @Override
    public String toString() {
        return "items{" +
                "productId=" + productId +
                ", title='" + title + '\'' +
                '}';
    }
}
