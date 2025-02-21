package com.codify.ioio.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Home {
    private int numberOfCustomers;
    private double totalSales;
    private double totalPayments;
    private double totalStockPrice;
}
