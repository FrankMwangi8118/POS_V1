package com.codify.ioio.Repository;

import com.codify.ioio.Model.TblPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo extends JpaRepository<TblPayment,Integer> {
}
