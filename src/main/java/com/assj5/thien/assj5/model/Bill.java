package com.assj5.thien.assj5.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@Entity
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long billId;
    private String billCode;
    private Timestamp billDate;
    private String customerName;
    private String customerPhone;
    private String email;
    private String address;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "billId")
    private List<BillDetail> billDetails;

    public Bill() {
    }
}
