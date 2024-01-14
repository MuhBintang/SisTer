/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bintang.nilai.vo;

import com.bintang.nilai.entity.Nilai;

/**
 *
 * @author Asus
 */
public class ResponseTemplate {
    private Nilai nilai;
    private Mahasiswa mahasiswa;
    private MataKuliah matakuliah;

    public ResponseTemplate() {
    }

    public ResponseTemplate(Nilai nilai, Mahasiswa mahasiswa, MataKuliah matakuliah) {
        this.nilai = nilai;
        this.mahasiswa = mahasiswa;
        this.matakuliah = matakuliah;
    }

    public Nilai getNilai() {
        return nilai;
    }

    public void setNilai(Nilai nilai) {
        this.nilai = nilai;
    }

    public Mahasiswa getMahasiswa() {
        return mahasiswa;
    }

    public void setMahasiswa(Mahasiswa mahasiswa) {
        this.mahasiswa = mahasiswa;
    }

    public MataKuliah getMatakuliah() {
        return matakuliah;
    }

    public void setMatakuliah(MataKuliah matakuliah) {
        this.matakuliah = matakuliah;
    }

    @Override
    public String toString() {
        return "ResponseTemplate{" + "nilai=" + nilai + ", mahasiswa=" + mahasiswa + ", matakuliah=" + matakuliah + '}';
    }
   
}
