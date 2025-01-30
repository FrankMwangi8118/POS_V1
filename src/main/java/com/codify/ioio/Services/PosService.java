package com.codify.ioio.Services;

import com.codify.ioio.Model.TblPayment;
import com.codify.ioio.Model.TblSales;
import com.codify.ioio.Model.TblStock;
import com.codify.ioio.Repository.CustomerRepo;
import com.codify.ioio.Repository.SalesRepo;
import com.codify.ioio.Repository.StockTblRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PosService {
private final  CustomerRepo customerRepo;
private final StockTblRepo stockTblRepo;
private final SalesRepo salesRepo;
private final FilterService filterService;

    public PosService(CustomerRepo customerRepo, StockTblRepo stockTblRepo, SalesRepo salesRepo, FilterService filterService) {
        this.customerRepo = customerRepo;
        this.stockTblRepo = stockTblRepo;
        this.salesRepo = salesRepo;
        this.filterService = filterService;
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




    public Integer salesCalc() {
     return calc( customerRepo.findAll());
    }

    private int calc(List<TblPayment> all) {
        double price=all.stream()
                .mapToDouble(TblPayment::getAmount)
                .sum();
        return (int)price;
    }


    public List <TblSales> allSales() {
        return salesRepo.findAll();
    }

    public List<List<TblSales>> filterSales(String filterParam) {

        return filterService.filterSales(filterParam);
    }

    public List<List<TblPayment>> filterPayment(String filterParam){
        return filterService.filterPayment(filterParam);
    }
}
