package com.example.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.AlamatMapper;
import com.example.dao.KeluargaMapper;
import com.example.model.AlamatModel;
import com.example.model.KeluargaModel;
import com.example.model.ResidentModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class KeluargaServiceDatabase implements KeluargaService {

	@Autowired
	private KeluargaMapper keluargaMapper;
	@Autowired
	private AlamatMapper alamatMapper;

	@Override
	public KeluargaModel selectKeluargaByNKK(String nkk) {
		// TODO Auto-generated method stub
		log.info("Select family with nkk {}", nkk);
		return keluargaMapper.selectKeluargaByNKK(nkk);
	}

	@Override
	public List<ResidentModel> selectResidencesDiKeluarga(String id_keluarga) {
		// TODO Auto-generated method stub
		log.info("Select residences in a family with family id {}", id_keluarga);
		return keluargaMapper.selectResidencesDiKeluarga(id_keluarga);
	}

	@Override
	public void tambahKeluarga(KeluargaModel keluarga) {
		// TODO Auto-generated method stub
		keluarga.setNomor_kk(generateNKK(keluarga));
		
		keluargaMapper.tambahKeluarga(keluarga);
	}

	@Override
	public List<KeluargaModel> selectKeluargaBySimilarNKK(String nkk) {
		// TODO Auto-generated method stub
		log.info("Select keluarga with similar nkk {}", nkk);
		return keluargaMapper.selectKeluargaBySimilarNKK(nkk);
	}
	
	public String generateNKK(KeluargaModel keluarga) {
		String nkk = "";
		AlamatModel alamat = alamatMapper.selectAlamatKeluargaByIdKelurahan(keluarga.getId_kelurahan());
		String kode_kecamatan = alamat.getKode_kecamatan().toString();
		
		LocalDate localDate = LocalDate.now();
		
		String[] temp = localDate.toString().split("-");
		String tanggal = temp[2];
		String bulan = temp[1];
		String tahun = temp[0].substring(2);
		
		String nkkSementara = kode_kecamatan.substring(0, 6) + tanggal + bulan + tahun;
		
		List<KeluargaModel> nkkDepanSama = this.selectKeluargaBySimilarNKK(nkkSementara);
		int digitTerakhirNKK = 0;
		
		if(nkkDepanSama.size() != 0) {
			digitTerakhirNKK = Integer.parseInt(nkkDepanSama.get(nkkDepanSama.size() - 1).getNomor_kk().substring(12)) + 1;
		} else {
			digitTerakhirNKK = 1;
		}
		String nkkBelakang = "" + digitTerakhirNKK;
		
		if(nkkBelakang.length() == 1) {
			nkkBelakang = "000" + nkkBelakang;
		} else if(nkkBelakang.length() == 2) {
			nkkBelakang = "00" + nkkBelakang;
		} else if(nkkBelakang.length() == 3) {
			nkkBelakang = "0" + nkkBelakang;
		}
		
		nkk += nkkSementara + nkkBelakang;
		log.info("select keluarga with nkk {}", nkk);
		return nkk;
	}

	@Override
	public void updateKeluarga(KeluargaModel keluarga) {
		// TODO Auto-generated method stub
		String nkkLama = keluarga.getNomor_kk();
		keluarga.setNomor_kk(generateNKK(keluarga));
		log.info("Keluarga with nkk " + nkkLama + " successfully updated");
		keluargaMapper.updateKeluarga(nkkLama, keluarga);
	}
}
