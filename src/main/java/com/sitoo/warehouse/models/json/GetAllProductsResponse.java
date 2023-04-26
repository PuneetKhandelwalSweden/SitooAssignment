package com.sitoo.warehouse.models.json;

import java.util.List;
import java.util.Objects;

public class GetAllProductsResponse {
    private long totalCount;
    private List<Items> items;

    public GetAllProductsResponse(long totalCount, List<Items> items) {
        this.totalCount = totalCount;
        this.items = items;
    }

    public GetAllProductsResponse() {
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public List<Items> getItems() {
        return items;
    }

    public void setItems(List<Items> items) {
        this.items = items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GetAllProductsResponse that = (GetAllProductsResponse) o;
        return totalCount == that.totalCount && Objects.equals(items, that.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalCount, items);
    }

    @Override
    public String toString() {
        return "GetAllProductsResponse{" +
                "totalCount=" + totalCount +
                ", items=" + items +
                '}';
    }
}
