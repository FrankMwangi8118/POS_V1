package com.codify.ioio.Services;

import com.codify.ioio.Model.Home;
import com.codify.ioio.Model.TblPayment;
import com.codify.ioio.Model.TblSales;
import com.codify.ioio.Model.TblStock;
import com.codify.ioio.Repository.CustomerRepo;
import com.codify.ioio.Repository.SalesRepo;
import com.codify.ioio.Repository.StockTblRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PosService {
private final  CustomerRepo customerRepo;
private final StockTblRepo stockTblRepo;
private final SalesRepo salesRepo;
private final FilterService filterService;
private final DashService dashService;

    public PosService(CustomerRepo customerRepo, StockTblRepo stockTblRepo, SalesRepo salesRepo, FilterService filterService, DashService dashService) {
        this.customerRepo = customerRepo;
        this.stockTblRepo = stockTblRepo;
        this.salesRepo = salesRepo;
        this.filterService = filterService;
        this.dashService = dashService;
    }

    public List<TblPayment> getAllPayments() {
       return customerRepo.findAll();

    }

    public Integer getCustomerBaseSize() {
        return getAllPayments().size();
    }



    public List<TblStock> allStock() {
        return stockTblRepo.findAll();
    }






    public int getTotalPayments(List<TblPayment> allPayments) {
        double summation=allPayments.stream()
                .mapToDouble(TblPayment::getAmount)
                .sum();
        return (int)summation;
    }



    public List <TblSales> allSales() {
        return salesRepo.findAll();
    }

    public List <TblSales> filterSales(String filterParam) {

        return filterService.filterSales(filterParam);
    }

    public Home filterDashData(String filterParam){
      return dashService.filterDashData(filterParam);
    }

    public List<TblPayment>filterPayment(String filterParam){
        return filterService.filterPayment(filterParam);
    }

    public Integer getTotalStockPrice(List<TblStock> tblStocks) {

        double summation=tblStocks.stream()
                .mapToDouble(TblStock::getPrice)
                .sum();
        return (int) summation;
    }

    public double getTotalSales(List<TblSales> allSales) {
        double summation=allSales.stream()
                .mapToDouble(TblSales::getTotalPrice)
                .sum();
        return (int) summation;

    }


    public Object getDashData(Integer totalStockPrice, double totalSales, int totalPayments,int baseSize) {
        return Home.builder()
                .numberOfCustomers(baseSize)
                .totalPayments(totalPayments)
                .totalSales(totalSales)
                .totalStockPrice(totalStockPrice)
                .build();
    }

    public Page<TblStock> allPageableStock(int page, int size) {

        Pageable pageable= PageRequest.of(page, size);
        return stockTblRepo.findAll(pageable);
    }
}
