/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bintang.nilai.service;

import com.bintang.nilai.entity.Nilai;
import com.bintang.nilai.repository.NilaiRepository;
import com.bintang.nilai.vo.Mahasiswa;
import com.bintang.nilai.vo.MataKuliah;
import com.bintang.nilai.vo.ResponseTemplate;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.data.jpa.domain.AbstractPersistable_.id;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Asus
 */
@Service
public class NilaiService {

    @Autowired
    private NilaiRepository nilaiRepository;

    @Autowired
    private RestTemplate restTemplate;
    
    public List<Nilai> getAll() {
        return nilaiRepository.findAll();
    }
    
    public void insert(Nilai nilai) {
        nilaiRepository.save(nilai);
    }
    
    public List<Nilai> getNilai() {
        return nilaiRepository.findAll();
    }
    
    public List<ResponseTemplate> getNilai(Long nilaiId) {
        List <ResponseTemplate> responseList = new ArrayList<>();

        List<Nilai> nilaiList = nilaiRepository.findByIdMahasiswa(nilaiId);

        for (Nilai nilai : nilaiList) {
            Mahasiswa mahasiswa
                    = restTemplate.getForObject("http://localhost:9001/api/v1/mahasiswa/"
                            + nilai.getIdmahasiswa(), Mahasiswa.class);
            MataKuliah matakuliah
                    = restTemplate.getForObject("http://localhost:9002/api/v1/matkul/"
                            + nilai.getIdmatakuliah(), MataKuliah.class);
            ResponseTemplate vo = new ResponseTemplate();

            vo.setNilai(nilai);
            vo.setMahasiswa(mahasiswa);
            vo.setMatakuliah(matakuliah);
            responseList.add(vo);

        }

        return responseList;
    }
    

    public void delete(Long id){
        boolean ada = nilaiRepository.existsById(id);
        
        if(!ada){
            throw new IllegalStateException("Nilai MataKuliah Ini Tidak Ada");
        }
        nilaiRepository.deleteById(id);
    
    }
    
    @Transactional
    public void update(Long id, Long idMahasiswa, Long idMatakuliah, Integer nilai){
        Nilai nilaiUpdate = nilaiRepository.findById(id)
        .orElseThrow(()-> new IllegalStateException("Nilai tidak ada"));
        
        if (idMahasiswa != null && !Objects.equals(nilaiUpdate.getIdmahasiswa(), idMahasiswa)) {
            nilaiUpdate.setIdmahasiswa(idMahasiswa);
        }
        
        if (idMatakuliah != null && !Objects.equals(nilaiUpdate.getIdmatakuliah(), idMatakuliah)) {
            nilaiUpdate.setIdmatakuliah(idMatakuliah);
        }
        
        if (nilai != null && !Objects.equals(nilaiUpdate.getNilai(), nilai)) {
            nilaiUpdate.setNilai(nilai);
        }
    }

}
