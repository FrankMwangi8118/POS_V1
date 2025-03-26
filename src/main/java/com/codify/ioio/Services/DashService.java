package com.codify.ioio.Services;

import com.codify.ioio.Model.Home;
import com.codify.ioio.Model.TblPayment;
import com.codify.ioio.Model.TblSales;
import com.codify.ioio.Model.TblStock;
import com.codify.ioio.Repository.CustomerRepo;
import com.codify.ioio.Repository.SalesRepo;
import com.codify.ioio.Repository.StockTblRepo;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
@Component
public class DashService {

    private final CustomerRepo customerRepo;
    private final SalesRepo salesRepo;
    private final StockTblRepo stockTblRepo;

    public DashService(CustomerRepo customerRepo, SalesRepo salesRepo, StockTblRepo stockTblRepo) {
        this.customerRepo = customerRepo;
        this.salesRepo = salesRepo;
        this.stockTblRepo = stockTblRepo;
    }


    public int totalSales(List<TblSales> sales) {
        return (int) sales.stream()
                .mapToDouble(TblSales::getPrice)
                .sum();
    }

    public int totalStockPricing(List<TblStock> stocks) {
        return (int) stocks.stream()
                .mapToDouble(TblStock::getPrice)
                .sum();
    }

    public int totalPayments(List<TblPayment> payments) {
        return (int) payments.stream()
                .mapToDouble(TblPayment::getAmount)
                .sum();
    }

    public Home filterDashData(String filterParam) {
        Timestamp[] dateRange = getDateRange(filterParam);
        Timestamp startDate = dateRange[0];
        Timestamp endDate = dateRange[1];
        List<TblPayment> users = customerRepo.findTransactionsBetweenDates(startDate, endDate);
        int baseSize = users.size();

        return Home.builder()


                .totalPayments(totalPayments(customerRepo.findTransactionsBetweenDates(startDate, endDate)))
                .numberOfCustomers(baseSize)
                .totalSales(totalSales(salesRepo.findSalesBetweenDates(startDate, endDate)))
                .totalStockPrice(totalStockPricing(stockTblRepo.getStockBetweenDates(startDate, endDate)))
                .build();
    }

    private Timestamp[] getDateRange(String param) {
        Calendar calendar = Calendar.getInstance();
        Timestamp endDate = new Timestamp(calendar.getTimeInMillis());

        switch (param.toLowerCase()) {
            case "week" -> calendar.add(Calendar.WEEK_OF_YEAR, -1);
            case "month" -> calendar.add(Calendar.MONTH, -1);
            case "year" -> calendar.add(Calendar.YEAR, -1);
            default -> throw new IllegalStateException("Unexpected value: " + param.toLowerCase());
        }
        Timestamp startDate = new Timestamp(calendar.getTimeInMillis());
        return new Timestamp[]{startDate, endDate};
    }
}
