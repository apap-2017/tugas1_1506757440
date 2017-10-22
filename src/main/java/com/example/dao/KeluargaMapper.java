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
public interface KeluargaMapper {
	
	@Select("SELECT * FROM keluarga WHERE nomor_kk = #{nkk}")
	KeluargaModel selectKeluargaByNKK(@Param("nkk") String nkk);
	
	@Select("SELECT * FROM penduduk WHERE id_keluarga = #{id_keluarga}")
	List<ResidentModel> selectResidencesDiKeluarga(@Param("id_keluarga") String id_keluarga);
	
	@Insert("INSERT INTO keluarga (nomor_kk, alamat, rt, rw, id_kelurahan, is_tidak_berlaku) VALUES (#{nomor_kk}, #{alamat}, #{rt}, #{rw}, #{id_kelurahan}, 0)")
	void tambahKeluarga(KeluargaModel keluarga);
	
	@Select("SELECT * FROM keluarga WHERE nomor_kk LIKE '%"+"${nkk}"+"%'")
	List<KeluargaModel> selectKeluargaBySimilarNKK(@Param("nkk") String nkk);
	
	@Update("UPDATE keluarga SET nomor_kk = #{keluarga.nomor_kk}, alamat = #{keluarga.alamat}, rt = #{keluarga.rt}, rw = #{keluarga.rw}, id_kelurahan = #{keluarga.id_kelurahan} WHERE nomor_kk = #{nkkLama}")
	void updateKeluarga(@Param("nkkLama") String nkkLama, @Param("keluarga") KeluargaModel keluarga);
}
