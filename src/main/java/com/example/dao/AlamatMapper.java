package com.example.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.model.AlamatModel;

@Mapper
public interface AlamatMapper {
	
	@Select("SELECT K.alamat, K.rt, K.rw, KL.id, KL.nama_kelurahan, KC.id, KC.kode_kecamatan, KC.nama_kecamatan, KO.id, KO.nama_kota "
			+ "FROM keluarga K, kelurahan KL, kecamatan KC, kota KO "
			+ "WHERE K.id = #{id_keluarga} AND K.id_kelurahan = KL.id AND KL.id_kecamatan = KC.id AND KC.id_kota = KO.id")
	AlamatModel selectAlamatKeluargaByIdKeluarga(@Param("id_keluarga") String id_keluarga);
	
	@Select("SELECT DISTINCT KL.id, KL.nama_kelurahan, KC.id, KC.kode_kecamatan, KC.nama_kecamatan, KO.id, KO.nama_kota "
			+ "FROM keluarga K, kelurahan KL, kecamatan KC, kota KO "
			+ "WHERE K.id_kelurahan = #{id_kelurahan} AND K.id_kelurahan = KL.id AND KL.id_kecamatan = KC.id AND KC.id_kota = KO.id")
	AlamatModel selectAlamatKeluargaByIdKelurahan(@Param("id_kelurahan") String id_kelurahan);
}
