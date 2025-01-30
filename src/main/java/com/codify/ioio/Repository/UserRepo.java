package com.codify.ioio.Repository;

import com.codify.ioio.Model.TblUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepo extends JpaRepository<TblUsers , Long> {
    Optional<TblUsers> findByUsername(String username);
}
