package com.codify.ioio.Services;

import com.codify.ioio.Commons.ApiResponse;
import com.codify.ioio.Model.Home;
import com.codify.ioio.Model.TblPayment;
import com.codify.ioio.Model.TblSales;
import com.codify.ioio.Model.TblStock;
import com.codify.ioio.Repository.CustomerRepo;
import com.codify.ioio.Repository.SalesRepo;
import com.codify.ioio.Repository.StockTblRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

@Component
public class FilterService {
    private final CustomerRepo customerRepo;
    private final PosService posService;
    private final SalesRepo salesRepo;
    private final StockTblRepo stockTblRepo;
    @PersistenceContext
    private final EntityManager entityManager;

    public FilterService(CustomerRepo customerRepo, @Lazy PosService posService, SalesRepo salesRepo, StockTblRepo stockTblRepo, EntityManager entityManager) {
        this.customerRepo = customerRepo;
        this.posService = posService;
        this.salesRepo = salesRepo;
        this.stockTblRepo = stockTblRepo;
        this.entityManager = entityManager;
    }

    public List<TblSales> filterSales(String filterParam) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<TblSales> cq = cb.createQuery(TblSales.class);
        Root<TblSales> root = cq.from(TblSales.class);


        Predicate predicate = null;
        switch (filterParam.toUpperCase()) {
            case "24HOURS":
                predicate = cb.greaterThanOrEqualTo(root.get("saleTime"), LocalDateTime.now().minusDays(1));
                break;
            case "WEEK":
                predicate = cb.greaterThanOrEqualTo(root.get("saleTime"), LocalDateTime.now().minusWeeks(1));
                break;
            case "MONTH":
                predicate = cb.greaterThanOrEqualTo(root.get("saleTime"), LocalDateTime.now().minusMonths(1));
                break;
            case "YEAR":
                predicate = cb.greaterThanOrEqualTo(root.get("saleTime"), LocalDateTime.now().minusYears(1));
                break;
            default:
                throw new IllegalArgumentException("Invalid filter parameter: " + filterParam);
        }

        // Set the predicate and execute the query
        cq.where(predicate);
        return Collections.singletonList((TblSales) entityManager.createQuery(cq).getResultList());

    }


    // filter payments


    public List<TblPayment> filterPayment(String filterParam) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<TblPayment> cq = cb.createQuery(TblPayment.class);
        Root<TblPayment> root = cq.from(TblPayment.class);


        Predicate predicate = null;
        switch (filterParam.toUpperCase()) {
            case "24HOURS":
                predicate = cb.greaterThanOrEqualTo(root.get("paymentTime"), LocalDateTime.now().minusDays(1));
                break;
            case "WEEK":
                predicate = cb.greaterThanOrEqualTo(root.get("paymentTime"), LocalDateTime.now().minusWeeks(1));
                break;
            case "MONTH":
                predicate = cb.greaterThanOrEqualTo(root.get("paymentTime"), LocalDateTime.now().minusMonths(1));
                break;
            case "YEAR":
                predicate = cb.greaterThanOrEqualTo(root.get("paymentTime"), LocalDateTime.now().minusYears(1));
                break;
            default:
                throw new IllegalArgumentException("Invalid filter parameter: " + filterParam);
        }

        // Set the predicate and execute the query
        cq.where(predicate);
        return Collections.singletonList((TblPayment) entityManager.createQuery(cq).getResultList());

    }




    // filter dashData


}





