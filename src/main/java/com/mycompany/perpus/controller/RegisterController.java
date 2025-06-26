/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.perpus.controller;

import com.mycompany.perpus.dao.MahasiswaDAO;
import com.mycompany.perpus.model.Mahasiswa;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class RegisterController {
    @FXML private TextField nimField;
    @FXML private TextField namaField;
    @FXML private TextField prodiField;
    @FXML private TextField angkatanField;
    @FXML private Label statusLabel;

    private MahasiswaDAO mahasiswaDAO = new MahasiswaDAO();

    @FXML
    private void submitRegistration() {
        String nim = nimField.getText().trim();
        String nama = namaField.getText().trim();
        String prodi = prodiField.getText().trim();
        String angkatan = angkatanField.getText().trim();

        if (nim.isEmpty() || nama.isEmpty() || prodi.isEmpty() || angkatan.isEmpty()) {
            statusLabel.setText("Semua field harus diisi!");
            return;
        }

        if (mahasiswaDAO.exists(nim)) {
            statusLabel.setText("NIM sudah terdaftar!");
        } else {
            Mahasiswa m = new Mahasiswa(nim, nama, prodi, angkatan);
            if (mahasiswaDAO.insert(m)) {
                statusLabel.setText("Pendaftaran berhasil!");
                clearFields();
            } else {
                statusLabel.setText("Pendaftaran gagal.");
            }
        }
    }

    private void clearFields() {
        nimField.clear();
        namaField.clear();
        prodiField.clear();
        angkatanField.clear();
    }
}
