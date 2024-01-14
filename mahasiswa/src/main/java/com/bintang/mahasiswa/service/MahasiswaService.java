/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bintang.mahasiswa.service;

import com.bintang.mahasiswa.entity.Mahasiswa;
import com.bintang.mahasiswa.repository.MahasiswaRepository;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Asus
 */
@Service
public class MahasiswaService {
    @Autowired
    private MahasiswaRepository mahasiswaRepository;
    
    public List<Mahasiswa> getAll(){
        return mahasiswaRepository.findAll();
    
    }
    
    public Mahasiswa getMahasiswa(Long id) {
        Optional<Mahasiswa> mahasiswaOptional = mahasiswaRepository.findById(id);
        return mahasiswaOptional.get();
    }
    
    public void insert(Mahasiswa mahasiswa){
        Optional<Mahasiswa> mahasiswaOptional = mahasiswaRepository.findMahasiswaByEmail(mahasiswa.getEmail());

        if (mahasiswaOptional.isPresent()) {
            throw new IllegalStateException("Email Sudah Ada");
        }

        mahasiswaRepository.save(mahasiswa);
    }
    
    public void delete(Long id){
        boolean ada = mahasiswaRepository.existsById(id);
        if(!ada){
            throw new IllegalStateException("Mahasiswa ini tidak ada");
        }
        mahasiswaRepository.deleteById(id);
    }
    
    @Transactional
    public void update(Long id, String nama, String email){
        Mahasiswa mahasiswa = mahasiswaRepository.findById(id).orElseThrow(()-> new IllegalStateException("Mahasiswa tidak ada"));
        if(nama!=null && nama.length()>0 && !Objects.equals(mahasiswa.getNama(), nama)){
            mahasiswa.setNama(nama);
        }
        
        if(email!=null && email.length()>0 && !Objects.equals(mahasiswa.getEmail(), email)){
            Optional<Mahasiswa> mahasiswaOptional = mahasiswaRepository.findMahasiswaByEmail(email);
            if(mahasiswaOptional.isPresent()){
                throw new IllegalStateException("Email sudah ada");
            }
            mahasiswa.setEmail(email);
        }
    }
}
