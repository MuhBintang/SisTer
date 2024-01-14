/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bintang.nilai.controller;

import com.bintang.nilai.entity.Nilai;
import com.bintang.nilai.service.NilaiService;
import com.bintang.nilai.vo.ResponseTemplate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Asus
 */
@RestController
@RequestMapping("api/v1/nilai")
public class NilaiController {
    @Autowired
    private NilaiService nilaiService;
    
    @GetMapping
    public List<Nilai> getAll(){
        return nilaiService.getNilai();
    }
    
    @GetMapping(path = "{id}")
    public List <ResponseTemplate> getNilai(@PathVariable("id") Long nilaiId) {
        return nilaiService.getNilai(nilaiId);
    }
    
    @PostMapping
    public void insert(@RequestBody Nilai nilai){
        nilaiService.insert(nilai);
    }
    
    @DeleteMapping(path = "{id}")
    public void delete(@PathVariable("id") Long id){
        nilaiService.delete(id);
    }
    
    @PutMapping(path = "{id}")
    public void update(@PathVariable("id") Long id,
            @RequestParam(required = false) Long idMahasiswa,
            @RequestParam(required = false) Long idMatakuliah,
            @RequestParam(required = false) Integer nilai){
        nilaiService.update(id, idMahasiswa, idMatakuliah, nilai);
    }
}
