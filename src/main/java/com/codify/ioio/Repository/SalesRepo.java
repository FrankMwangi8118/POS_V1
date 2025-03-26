package com.codify.ioio.Repository;

import com.codify.ioio.Model.TblSales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface SalesRepo extends JpaRepository<TblSales, Integer> {
    @Query("SELECT t FROM TblSales t WHERE t.saleTime BETWEEN :startDate AND :endDate")
    List<TblSales> findSalesBetweenDates(@Param("startDate") Timestamp startDate,
                                         @Param("endDate") Timestamp endDate);

}
