package com.codify.ioio.Repository;

import com.codify.ioio.Model.TblStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockTblRepo extends JpaRepository<TblStock,Integer> {
}
