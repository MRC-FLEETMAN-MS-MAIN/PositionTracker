package com.example.demo;

import com.example.demo.entity.PosEnt;
import com.example.demo.repository.PosRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

@Service
public class Dataprocessing {

    @Autowired
    private PosRepository posrepo;

    //Map<String, Queue<Position>>positionqueues;

    //Queue<Position> positions = new PriorityQueue<Position>();
    HashMap<String, Queue<Position>> hash_map = new HashMap<String, Queue<Position>>();

    //Queue<Position> cusqueue = new PriorityQueue<Position>();
    Queue<Position> cusqueue  = new LinkedList<>();


    public void updateposition(String message){

        JSONObject obj = new JSONObject(message);
        String vehiclename = obj.get("vehicle").toString();

        System.out.print("Checking exec");
   //     Queue<Position> temqueue = hash_map.get(vehiclename);

   //     if (temqueue == null)
   //     {
   //             temqueue  = new LinkedList<>();
  //              hash_map.put(vehiclename,temqueue);
   //     }



   //     Position pos = new Position(new BigDecimal(obj.get("lat").toString()),new BigDecimal(obj.get("long").toString()));
   //     temqueue.add(pos);
        PosEnt posent = new PosEnt();
        posent.setVehiclename(vehiclename);
        posent.setLat(new BigDecimal(obj.get("lat").toString()));
        posent.setLongitude(new BigDecimal(obj.get("long").toString()));
        posrepo.save(posent);
   //     System.out.println("The size of the queue is "  + temqueue.size());


    }

    /*
    public Position getlatestpos(String vehiclename) throws Exception {
        Queue<Position> queue = hash_map.get(vehiclename);
        if(queue == null){
            throw new Exception("Problem with latest pos update as the queue is null");
        }
        System.out.println("The size of the queue is "  + queue.size());
        return queue.remove();

    }  */


    public Position getposdb() throws Exception{
        PosEnt data = posrepo.poslatest();
        return new Position(data.getLat(),data.getLongitude());
    }

    public String getvehiclename(){

   //     for ( String vehicle : this.hash_map.keySet() ) {
  //        //  System.out.println( key );
   //         return vehicle;
   //     }

        String data = posrepo.vehlatest();
        return data;



     //   return "";

    }

}
