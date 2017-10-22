package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.AlamatModel;
import com.example.model.KeluargaModel;
import com.example.model.ResidentModel;
import com.example.service.AlamatService;
import com.example.service.KeluargaService;
import com.example.service.ResidentService;

@Controller
public class SistemController {
	@Autowired
	ResidentService residentDAO;
	@Autowired
	KeluargaService keluargaDAO;
	@Autowired
	AlamatService alamatDAO;
	
	@RequestMapping("/penduduk/tambah")
	public String tambahPenduduk(Model model) {
		ResidentModel resident = new ResidentModel();
		
		model.addAttribute("resident", resident);
		
		return "form-add-penduduk";
	}
	
	@RequestMapping("/keluarga/tambah")
	public String tambahKeluarga(Model model) {
		KeluargaModel keluarga = new KeluargaModel();
		
		model.addAttribute("keluarga", keluarga);
		
		return "form-add-keluarga";
	}
	
	@RequestMapping("/penduduk")
	public String viewPenduduk(@RequestParam(value = "nik", required = true) String nik, Model model) {
		ResidentModel resident = residentDAO.selectResident(nik);
		KeluargaModel keluarga = residentDAO.selectKeluarga(resident.getId_keluarga());
		AlamatModel alamat = alamatDAO.selectAlamatKeluargaByIdKeluarga(keluarga.getId());
		
		
		model.addAttribute("resident", resident);
		model.addAttribute("keluarga", keluarga);
		model.addAttribute("alamat", alamat);
		
		return "view-penduduk";
	}
	
	@RequestMapping(value = "/penduduk/tambah", method = RequestMethod.POST)
	public String tambahPendudukSubmit(ResidentModel resident, Model model) {
		residentDAO.tambahPenduduk(resident);
		model.addAttribute("resident", resident);
		return "success-add-penduduk";
	}
	
	@RequestMapping("/keluarga")
	public String viewKeluarga(@RequestParam(value = "nkk", required = true) String nkk, Model model) {
		KeluargaModel keluarga = keluargaDAO.selectKeluargaByNKK(nkk);
		List<ResidentModel> residences = keluargaDAO.selectResidencesDiKeluarga(keluarga.getId());
		AlamatModel alamat = alamatDAO.selectAlamatKeluargaByIdKeluarga(keluarga.getId());
		
		model.addAttribute("keluarga",keluarga);
		model.addAttribute("residences", residences);
		model.addAttribute("alamat", alamat);
		
		return "view-keluarga";
		
	}
	
	@RequestMapping(value = "/keluarga/tambah", method = RequestMethod.POST)
	public String tambahKeluargaSubmit(KeluargaModel keluarga, Model model) {
		keluargaDAO.tambahKeluarga(keluarga);
		model.addAttribute("keluarga", keluarga);
		return "success-add-keluarga";
	}
	
	@RequestMapping("/penduduk/ubah/{nik}")
	public String updatePendudukPath(@PathVariable(value = "nik") String nik, Model model) {
		ResidentModel resident = residentDAO.selectResident(nik);
		if(resident != null) {
			model.addAttribute("resident", resident);
			return "form-update-penduduk";
		}
		model.addAttribute("nik", nik);
		return "";
	}
	
	@RequestMapping(value = "/penduduk/ubah/{nik}", method = RequestMethod.POST)
	public String updatePendudukSubmit(@PathVariable(value = "nik") String nik, ResidentModel resident, Model model) {
		residentDAO.updatePenduduk(resident);
		model.addAttribute("nik", nik);
		return "success-update-penduduk";
	}
	
	@RequestMapping("/keluarga/ubah/{nomor_kk}")
	public String updateKeluargaPath(@PathVariable(value = "nomor_kk") String nomor_kk, Model model) {
		KeluargaModel keluarga = keluargaDAO.selectKeluargaByNKK(nomor_kk);
		if(keluarga != null) {
			model.addAttribute("keluarga", keluarga);
			return "form-update-keluarga";
		}
		model.addAttribute("nkk", nomor_kk);
		return "";
	}
	
	@RequestMapping(value = "/keluarga/ubah/{nomor_kk}", method = RequestMethod.POST)
	public String updateKeluargaSubmit(@PathVariable(value = "nomor_kk") String nomor_kk, KeluargaModel keluarga, Model model) {
		keluargaDAO.updateKeluarga(keluarga);
		model.addAttribute("nkk", nomor_kk);
		return "success-update-keluarga";
	}
}
