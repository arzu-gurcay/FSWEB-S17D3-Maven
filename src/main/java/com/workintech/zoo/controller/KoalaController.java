package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Koala;
import jakarta.annotation.PostConstruct;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/koalas")
public class KoalaController {

    private final Map<Integer, Koala> koalas = new ConcurrentHashMap<>();

    @PostConstruct
    public void init(){
        Koala k1 =  new Koala(1,"Koko",8.5,20.0,"Female");
        koalas.put(k1.getId(),k1);
    }

    @GetMapping
    public List<Koala> getAll(){
        return new ArrayList<>(koalas.values());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Koala> getById(@PathVariable Integer id){
        Koala k= koalas.get(id);
        return ResponseEntity.ok(k);
    }

    @PostMapping
    public ResponseEntity<Koala> createdKoala(@RequestBody Koala koala){
        koalas.put(koala.getId(),koala);
        return ResponseEntity.ok(koala);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Koala> updatedKoala(@PathVariable Integer id,@RequestBody Koala koala){
        koala.setId(id);
        koalas.put(id,koala);
        return ResponseEntity.ok(koala);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Koala> deleteKoala(@PathVariable Integer id){
        Koala removed = koalas.remove(id);
        return ResponseEntity.ok(removed);
    }
}

