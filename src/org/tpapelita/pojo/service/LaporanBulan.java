package org.tpapelita.pojo.service;

import java.io.Serializable;

public class LaporanBulan implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String tanggal;
	private int pendapatan;
	private int pengeluaran;
	
	public LaporanBulan() {
		// TODO Auto-generated constructor stub
	}
	
	public LaporanBulan(String tanggal, int pendapatan, int pengeluaran) {
		this.tanggal = tanggal;
		this.pendapatan = pendapatan;
		this.pengeluaran = pengeluaran;
	}

	public String getTanggal() {
		return tanggal;
	}

	public void setTanggal(String tanggal) {
		this.tanggal = tanggal;
	}

	public int getPendapatan() {
		return pendapatan;
	}

	public void setPendapatan(int pendapatan) {
		this.pendapatan = pendapatan;
	}

	public int getPengeluaran() {
		return pengeluaran;
	}

	public void setPengeluaran(int pengeluaran) {
		this.pengeluaran = pengeluaran;
	}
}
