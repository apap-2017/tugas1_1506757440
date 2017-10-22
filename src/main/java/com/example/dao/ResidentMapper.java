package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.model.KeluargaModel;
import com.example.model.ResidentModel;

@Mapper
public interface ResidentMapper {
	
	@Select("SELECT * FROM penduduk WHERE nik = #{nik}")
	ResidentModel selectResident(@Param("nik") String nik);
	
	@Select("SELECT * FROM keluarga WHERE id = #{id}")
	KeluargaModel selectKeluarga(@Param("id") String id);
	
	@Select("SELECT * FROM penduduk WHERE nik LIKE '%"+"${nik}"+"%'")
	List<ResidentModel> selectResidentBySimilarNIK(@Param("nik") String nik);
		
	@Insert("INSERT INTO penduduk (nik, nama, tempat_lahir, tanggal_lahir, jenis_kelamin, is_wni, id_keluarga, agama, pekerjaan, status_perkawinan, status_dalam_keluarga, golongan_darah, is_wafat) "
			+ "VALUES (#{nik}, #{nama}, #{tempat_lahir}, #{tanggal_lahir}, #{jenis_kelamin}, #{is_wni}, #{id_keluarga}, #{agama}, #{pekerjaan}, #{status_perkawinan}, #{status_dalam_keluarga}, #{golongan_darah}, #{is_wafat})")
	void tambahPenduduk(ResidentModel resident);
	
	@Update("UPDATE penduduk SET nik = #{resident.nik}, nama = #{resident.nama}, tempat_lahir = #{resident.tempat_lahir}, tanggal_lahir = #{resident.tanggal_lahir}, jenis_kelamin = #{resident.jenis_kelamin}, is_wni = #{resident.is_wni}, id_keluarga = #{resident.id_keluarga}, "
			+ "agama = #{resident.agama}, pekerjaan = #{resident.pekerjaan}, status_perkawinan = #{resident.status_perkawinan}, status_dalam_keluarga = #{resident.status_dalam_keluarga}, golongan_darah = #{resident.golongan_darah} "
			+ "WHERE nik = #{nikLama}")
	void updatePenduduk(@Param("nikLama") String nikLama, @Param("resident") ResidentModel resident);
}
