package org.tpapelita.pojo.service;

import java.io.Serializable;

public class LaporanTahun implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String bulan;
	private int pendapatan;
	private int pengeluaran;
	
	public LaporanTahun() {
		// TODO Auto-generated constructor stub
	}
	
	public LaporanTahun(String bulan, int pendapatan, int pengeluaran) {
		this.bulan = bulan;
		this.pendapatan = pendapatan;
		this.pengeluaran = pengeluaran;
	}

	public String getBulan() {
		return bulan;
	}

	public void setBulan(String bulan) {
		this.bulan = bulan;
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
