package com.example.service;

import java.util.List;

import com.example.model.KeluargaModel;
import com.example.model.ResidentModel;

public interface ResidentService {
	ResidentModel selectResident(String nik);
	
	List<ResidentModel> selectResidentBySimilarNIK(String nik);
	
	KeluargaModel selectKeluarga(String id);
	
	void tambahPenduduk(ResidentModel resident);
	
	void updatePenduduk(ResidentModel resident);
}
