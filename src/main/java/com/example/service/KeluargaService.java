package com.example.service;

import java.util.List;

import com.example.model.KeluargaModel;
import com.example.model.ResidentModel;

public interface KeluargaService {
	
	KeluargaModel selectKeluargaByNKK(String nkk);
	
	List<ResidentModel> selectResidencesDiKeluarga(String id_keluarga);
	
	void tambahKeluarga(KeluargaModel keluarga);
	
	List<KeluargaModel> selectKeluargaBySimilarNKK(String nkk);
	
	void updateKeluarga(KeluargaModel keluarga);
}
