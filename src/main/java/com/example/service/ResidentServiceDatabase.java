package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.AlamatMapper;
import com.example.dao.ResidentMapper;
import com.example.model.AlamatModel;
import com.example.model.KeluargaModel;
import com.example.model.ResidentModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ResidentServiceDatabase implements ResidentService{
	@Autowired
	private ResidentMapper residentMapper;
	@Autowired
	private AlamatMapper alamatMapper;

	@Override
	public ResidentModel selectResident(String nik) {
		// TODO Auto-generated method stub
		log.info("Select resident with nik {}", nik);
		return residentMapper.selectResident(nik);
	}

	@Override
	public List<ResidentModel> selectResidentBySimilarNIK(String nik) {
		// TODO Auto-generated method stub
		log.info("Select resident with similar nik {}", nik);
		return residentMapper.selectResidentBySimilarNIK(nik);
	}

	@Override
	public KeluargaModel selectKeluarga(String id) {
		// TODO Auto-generated method stub
		log.info("Select keluarga resident with id keluarga {}", id);
		return residentMapper.selectKeluarga(id);
	}

	@Override
	public void tambahPenduduk(ResidentModel resident) {
		// TODO Auto-generated method stub
		resident.setNik(generateNIK(resident));
		
		residentMapper.tambahPenduduk(resident);
		
	}
	
	public String generateNIK(ResidentModel resident) {
		
		String nik = "";
		AlamatModel alamat = alamatMapper.selectAlamatKeluargaByIdKeluarga(resident.getId_keluarga());
		String kode_kecamatan = alamat.getKode_kecamatan().toString();
		
		String[] temp = resident.getTanggal_lahir().toString().split("-");
		String tanggal = temp[2];
		String bulan = temp[1];
		String tahun = temp[0].substring(2);
		
		if(resident.getJenis_kelamin() == 1) {
			tanggal = "" + (Integer.parseInt(tanggal) + 40);
		}
		String nikSementara = kode_kecamatan.substring(0, kode_kecamatan.length() - 1) + tanggal + bulan + tahun;
		
		List<ResidentModel> nikDepanSama = this.selectResidentBySimilarNIK(nikSementara);
		int digitTerakhirNIK = 0;
		
		if(nikDepanSama.size() != 0) {
			digitTerakhirNIK = Integer.parseInt(nikDepanSama.get(nikDepanSama.size() - 1).getNik().substring(12)) + 1;
		} else {
			digitTerakhirNIK = 1;
		}
		String nikBelakang = "" + digitTerakhirNIK;
		
		if(nikBelakang.length() == 1) {
			nikBelakang = "000" + nikBelakang;
		} else if(nikBelakang.length() == 2) {
			nikBelakang = "00" + nikBelakang;
		} else if(nikBelakang.length() == 3) {
			nikBelakang = "0" + nikBelakang;
		}
		
		nik += nikSementara + nikBelakang;
		
		return nik;
	}

	@Override
	public void updatePenduduk(ResidentModel resident) {
		// TODO Auto-generated method stub
		String nikLama = resident.getNik();
		resident.setNik(generateNIK(resident));
		log.info("Resident with nik " + nikLama + " successfully updated");
		residentMapper.updatePenduduk(nikLama, resident);
	}
}
