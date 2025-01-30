package com.codify.ioio.Repository;

import com.codify.ioio.Model.TblSales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesRepo extends JpaRepository<TblSales,Integer> {
}
