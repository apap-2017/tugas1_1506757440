package com.example.model;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlamatModel {
	
	@NotNull
	private String alamat;
	
	@NotNull
	private String id_keluarga;
	
	@NotNull
	private String id_kelurahan;
	
	@NotNull
	private String id_kecamatan;
	
	@NotNull
	private String id_kota;
	
	@NotNull
	private String kode_kecamatan;
	
	@NotNull
	private String nama_kelurahan;
	
	@NotNull
	private String nama_kecamatan;
	
	@NotNull
	private String nama_kota;
	
	@NotNull
	private String rt;
	
	@NotNull
	private String rw;
}
