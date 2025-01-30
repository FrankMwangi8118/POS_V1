package com.codify.ioio.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.text.DecimalFormat;

@Entity
@Data
public class TblUsers {
    @Id
    private Long id;
    private String username;
    private String password;


}