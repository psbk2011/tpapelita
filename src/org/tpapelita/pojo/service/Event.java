package org.tpapelita.pojo.service;

import java.io.Serializable;
import java.util.Calendar;

public class Event implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String namaEvent;
	private Calendar tglMulai;
	private Calendar tglSelesai;
	private Calendar jamMulai;
	private Calendar jamSelesai;
	private Calendar menitMulai;
	private Calendar menitSelesai;
	
	public Event(String namaEvent, Calendar tglMulai, Calendar tglSelesai, Calendar jamMulai, Calendar jamSelesai, Calendar menitMulai, Calendar menitSelesai) {
		this.namaEvent = namaEvent;
		this.tglMulai = tglMulai;
		this.tglSelesai = tglSelesai;
		this.jamMulai = jamMulai;
		this.jamSelesai = jamSelesai;
		this.menitMulai = menitMulai;
		this.menitSelesai = menitSelesai;
	}

	public String getNamaEvent() {
		return namaEvent;
	}

	public void setNamaEvent(String namaEvent) {
		this.namaEvent = namaEvent;
	}

	public Calendar getTglMulai() {
		return tglMulai;
	}

	public void setTglMulai(Calendar tglMulai) {
		this.tglMulai = tglMulai;
	}

	public Calendar getTglSelesai() {
		return tglSelesai;
	}

	public void setTglSelesai(Calendar tglSelesai) {
		this.tglSelesai = tglSelesai;
	}

	public Calendar getJamMulai() {
		return jamMulai;
	}

	public void setJamMulai(Calendar jamMulai) {
		this.jamMulai = jamMulai;
	}

	public Calendar getJamSelesai() {
		return jamSelesai;
	}

	public void setJamSelesai(Calendar jamSelesai) {
		this.jamSelesai = jamSelesai;
	}

	public Calendar getMenitMulai() {
		return menitMulai;
	}

	public void setMenitMulai(Calendar menitMulai) {
		this.menitMulai = menitMulai;
	}

	public Calendar getMenitSelesai() {
		return menitSelesai;
	}

	public void setMenitSelesai(Calendar menitSelesai) {
		this.menitSelesai = menitSelesai;
	}
}
