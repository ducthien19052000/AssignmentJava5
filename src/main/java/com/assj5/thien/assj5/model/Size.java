package com.assj5.thien.assj5.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Size {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long sizeId;
    private String sizeName;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "sizeId")
    private List<ProductDetail> productDetails;

    public Size() {
    }
}
