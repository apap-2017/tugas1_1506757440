package com.example.service;

import com.example.model.AlamatModel;

public interface AlamatService {
	
	AlamatModel selectAlamatKeluargaByIdKeluarga(String id_keluarga);
	
	AlamatModel selectAlamatKeluargaByIdKelurahan(String id_kelurahan);
}
