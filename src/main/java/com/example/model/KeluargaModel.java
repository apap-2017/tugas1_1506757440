package com.example.model;

import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KeluargaModel {
	
	@NotNull
	private String id;
	
	@NotNull
	private String nomor_kk;
	
	@NotNull
	private String alamat;
	
	@NotNull
	private String rt;
	
	@NotNull
	private String rw;
	
	@NotNull
	private String id_kelurahan;
	
	@NotNull
	private int is_tidak_berlaku;
	
	private List<ResidentModel> residences;
}