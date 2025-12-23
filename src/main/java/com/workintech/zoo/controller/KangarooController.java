package com.workintech.zoo.controller;


import com.workintech.zoo.entity.Kangaroo;
import com.workintech.zoo.exceptions.ZooException;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/kangaroos")
public class KangarooController {
    private final Map<Integer, Kangaroo> kangaroos = new ConcurrentHashMap<>();

    @PostConstruct
    public void init(){
        Kangaroo k1 = new Kangaroo(1,"Jack",1.2,75.0,"male",false);
        kangaroos.put(k1.getId(),k1);
    }

    @GetMapping
    public List<Kangaroo> getAll(){
        return new ArrayList<>(kangaroos.values());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Kangaroo> getById(@PathVariable Integer id){
        Kangaroo kangaroo = kangaroos.get(id);

        if(kangaroo==null){
            throw new ZooException("Kangaroo with id " + id + " not found ", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(kangaroo);
    }

    @PostMapping
    public ResponseEntity<Kangaroo> createKangaroo(@RequestBody Kangaroo kangaroo){
        if(kangaroo.getId()==null){
            throw new ZooException("Kangaroo with id is null ", HttpStatus.BAD_REQUEST);
        }
        kangaroos.put(kangaroo.getId(), kangaroo);
        return ResponseEntity.ok(kangaroo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Kangaroo> updateKangaroo(@PathVariable Integer id,@RequestBody Kangaroo kangaroo){
        kangaroo.setId(id);
        kangaroos.put(id,kangaroo);
        return ResponseEntity.ok(kangaroo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Kangaroo> deleteKangaroo(@PathVariable Integer id){
        Kangaroo removed = kangaroos.remove(id);
        return ResponseEntity.ok(removed);
    }
}

