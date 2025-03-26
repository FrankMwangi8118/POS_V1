package com.codify.ioio.Repository;

import com.codify.ioio.Model.TblPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.sql.Timestamp;
import java.util.List;

@Repository
public interface CustomerRepo extends JpaRepository<TblPayment, Integer> {

    @Query("SELECT c FROM TblPayment c WHERE c.paymentTime BETWEEN :startDate AND :endDate")
    List<TblPayment> findTransactionsBetweenDates(@Param("startDate") Timestamp startDate,
                                                  @Param("endDate") Timestamp endDate);
}
