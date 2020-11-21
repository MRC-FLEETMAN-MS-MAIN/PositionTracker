package com.example.demo.repository;

import com.example.demo.entity.PosEnt;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface PosRepository extends CrudRepository<PosEnt,Integer> {

    @Query(nativeQuery = true,value = "call poslatest")
    PosEnt poslatest();
    
    @Query(nativeQuery = true,value = "call vehiclesc")
    String vehlatest();
}
