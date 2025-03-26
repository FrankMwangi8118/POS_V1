package com.codify.ioio.Repository;

import com.codify.ioio.Model.TblStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface StockTblRepo extends JpaRepository<TblStock,Integer> {
    @Query("SELECT s FROM TblStock s WHERE s.addTime BETWEEN :startDate AND :endDate")
    List<TblStock>getStockBetweenDates(@Param("startDate")Timestamp startDate,
                                       @Param("endDate")Timestamp endDate);
}
