package com.example.demo;

import com.example.demo.entity.PosEnt;
import com.example.demo.repository.PosRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class Dataprocessing {

    @Autowired
    private PosRepository posrepo;


    public void updateposition(String message){

        JSONObject obj = new JSONObject(message);
        String vehiclename = obj.get("vehicle").toString();

        PosEnt posent = new PosEnt();
        posent.setVehiclename(vehiclename);
        posent.setLat(new BigDecimal(obj.get("lat").toString()));
        posent.setLongitude(new BigDecimal(obj.get("long").toString()));
        posrepo.save(posent);

    }


    public Position getposdb() throws Exception{
        PosEnt data = posrepo.poslatest();
        return new Position(data.getLat(),data.getLongitude());
    }

    public String getvehiclename(){

        String data = posrepo.vehlatest();
        return data;
    }

}
