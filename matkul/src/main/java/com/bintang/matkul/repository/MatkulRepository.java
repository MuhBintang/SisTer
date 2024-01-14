/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bintang.matkul.repository;

import com.bintang.matkul.entity.Matkul;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Asus
 */
@Repository
public interface MatkulRepository extends JpaRepository<Matkul, Long> {

    public Optional<Matkul> findMatkulByNama(String nama);

    public Optional<Matkul> findMatkulByKode(String kode);

    public Optional<Matkul> findMatkulBySks(Integer sks);
    
}
