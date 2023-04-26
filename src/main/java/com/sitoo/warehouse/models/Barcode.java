package com.sitoo.warehouse.models;

import jakarta.persistence.*;
import org.springframework.lang.NonNull;

import java.util.Objects;

@Entity
@Table(name="barcode")
public class Barcode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "barcode_id")
    private long barcodeId;
    @NonNull
    @Column(unique = true)
    private String barcode;

    @ManyToOne(fetch = FetchType.LAZY)
    private Products products;

    public Barcode(long barcodeId, @NonNull String barcode) {
        this.barcodeId = barcodeId;
        this.barcode = barcode;
    }

    public Barcode(@NonNull String barcode) {
        this.barcode = barcode;
    }

    public Barcode() {
    }

    public long getBarcodeId() {
        return barcodeId;
    }

    public void setBarcodeId(long barcodeId) {
        this.barcodeId = barcodeId;
    }

    @NonNull
    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(@NonNull String barcode) {
        this.barcode = barcode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Barcode barcode1 = (Barcode) o;
        return barcodeId == barcode1.barcodeId && barcode.equals(barcode1.barcode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(barcodeId, barcode);
    }

    @Override
    public String toString() {
        return "Barcode{" +
                "barcodeId=" + barcodeId +
                ", barcode='" + barcode + '\'' +
                '}';
    }
}
