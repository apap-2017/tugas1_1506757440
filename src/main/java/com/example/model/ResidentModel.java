package com.example.model;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResidentModel {
		
		@NotNull
		private String id;
		
		@NotNull
		private String nik;
		
		@NotNull
		private String nama;
		
		@NotNull
		private String tempat_lahir;
		
		@NotNull
		private String tanggal_lahir;
		
		@NotNull
		private int jenis_kelamin;
		
		@NotNull
		private int is_wni;
		
		@NotNull
		private String id_keluarga;
		
		@NotNull
		private String agama;
		
		@NotNull
		private String pekerjaan;
		
		@NotNull
		private String status_perkawinan;
		
		@NotNull
		private String status_dalam_keluarga;
		
		@NotNull
		private String golongan_darah;
		
		@NotNull
		private int is_wafat;
}
