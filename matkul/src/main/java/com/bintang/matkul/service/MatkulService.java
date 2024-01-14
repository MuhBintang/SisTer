/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bintang.matkul.service;

import com.bintang.matkul.entity.Matkul;
import com.bintang.matkul.repository.MatkulRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Asus
 */
@Service
public class MatkulService {
    @Autowired
    private  MatkulRepository matkulrepository;
    
    public List<Matkul>getAll(){
        return matkulrepository.findAll();
    }
    
    public Matkul getmatkul(Long id){
        Optional<Matkul> matkulOptional = matkulrepository.findById(id);
        return matkulOptional.get();
    }
    
    public void insert(Matkul matkul){
        Optional<Matkul> matkulOptional = matkulrepository.findMatkulByNama(matkul.getNama());
        Optional<Matkul> matkulOptionalkode = matkulrepository.findMatkulByKode(matkul.getKode());
        if (matkulOptional.isPresent()) {
            throw new IllegalStateException("nama mata kuliah ini sudah ada");
        }
        if (matkulOptionalkode.isPresent()) {
            throw new IllegalStateException("kode mata kuliah ini sudah ada");
        }
        
        matkulrepository.save(matkul);
    }
    
    public void delete (Long id){
        boolean ada = matkulrepository.existsById(id);
        
        if (!ada) {
            throw new IllegalStateException("mata kuliah ini tidak ada");
        }
        matkulrepository.deleteById(id);
    }
    
    @Transactional
    public void update(Long id, String kode, String nama, Integer sks){
        Matkul matkul = matkulrepository.findById(id).orElseThrow(()-> new IllegalStateException("mata kuliah ini tidak ada"));
        
        if (nama != null && nama.length()>0 && !Objects.equals(matkul.getNama(),nama)) {
            matkul.setNama(nama);
        }
        if (kode != null && kode.length()>0 && !Objects.equals(matkul.getKode(),kode)) {
            Optional<Matkul> mahaOptionalkode = matkulrepository.findMatkulByKode(kode);
            if (mahaOptionalkode.isPresent()) {
                throw  new IllegalStateException("kode ini sudah ada");
            }
            matkul.setKode(kode);
        }
         if (nama != null && nama.length()>0 && !Objects.equals(matkul.getNama(),nama)) {
            Optional<Matkul> mahaOptional = matkulrepository.findMatkulByNama(nama);
            if (mahaOptional.isPresent()) {
                throw  new IllegalStateException("nama mata kuliah ini sudah ada");
            }
            matkul.setKode(nama);
        }
         if (sks != null && !Objects.equals(matkul.getSks(),sks)) {
            matkul.setSks(sks);
        }
        
    }
    
}
