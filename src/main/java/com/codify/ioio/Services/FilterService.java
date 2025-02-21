package com.codify.ioio.Services;

import com.codify.ioio.Model.TblPayment;
import com.codify.ioio.Model.TblSales;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Component
public class FilterService {
@PersistenceContext
    private final EntityManager entityManager;

    public FilterService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List <TblSales> filterSales(String filterParam) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<TblSales> cq=cb.createQuery(TblSales.class);
        Root<TblSales> root=cq.from(TblSales.class);



        Predicate predicate=null;
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
        CriteriaQuery<TblPayment> cq=cb.createQuery(TblPayment.class);
        Root<TblPayment> root=cq.from(TblPayment.class);



        Predicate predicate=null;
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

    public List<Object> filterDashData(String filterParam) {
        List<Object> filteredDataList = new ArrayList<>();
        List<TblPayment> tblPaymentList = filterPayment(filterParam);
        List <TblSales> filteredSalesList = filterSales(filterParam);

        if (!tblPaymentList.isEmpty()) {
            filteredDataList.addAll(tblPaymentList);
        }
        if (!filteredSalesList.isEmpty()) {
            filteredDataList.addAll(filteredSalesList);
        }

        return filteredDataList;
    }

}





