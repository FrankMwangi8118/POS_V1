package com.codify.ioio.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Data
public class TblStock {
    @Id
    private int id;
    private String itemName;
    private int quantity;
    private double price;
    private Timestamp addTime;

}
