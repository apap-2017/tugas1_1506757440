package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.AlamatMapper;
import com.example.model.AlamatModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AlamatServiceDatabase implements AlamatService {
	
	@Autowired
	private AlamatMapper alamatMapper;
	
	@Override
	public AlamatModel selectAlamatKeluargaByIdKeluarga(String id_keluarga) {
		// TODO Auto-generated method stub
		log.info("Select address with nkk {}", id_keluarga);
		return alamatMapper.selectAlamatKeluargaByIdKeluarga(id_keluarga);
	}
	
	@Override
	public AlamatModel selectAlamatKeluargaByIdKelurahan(String id_kelurahan) {
		// TODO Auto-generated method stub
		log.info("Select address with id kelurahan {}", id_kelurahan);
		return alamatMapper.selectAlamatKeluargaByIdKelurahan(id_kelurahan);
	}
}
