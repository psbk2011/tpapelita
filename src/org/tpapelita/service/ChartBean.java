package org.tpapelita.service;

import java.io.Serializable;

import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.tpapelita.pojo.service.LaporanBulan;
import org.tpapelita.pojo.service.LaporanTahun;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class ChartBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ChartBean() {
		setMax(500);
		createReportData();
		createMap();
		createGraph();
	}

	// Source Select Box

	private int max;
	private String pilih1;
	private String pilih2;
	private Map<String, String> select1 = new HashMap<String, String>();
	private ArrayList<String> select2;
	private Map<String, String> tampung = new TreeMap<String, String>();
	private Map<String, Map<String, String>> data = new TreeMap<String, Map<String, String>>();
	private Map<String, String> tahun = new TreeMap<String, String>();
	private Map<String, String> bulan = new TreeMap<String, String>();
	private Map<String, LaporanTahun> mapTahun = new TreeMap<String, LaporanTahun>();
	private Map<String, LaporanBulan> mapBulan = new TreeMap<String, LaporanBulan>();

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public String getPilih1() {
		return pilih1;
	}

	public void setPilih1(String pilih1) {
		this.pilih1 = pilih1;
	}

	public String getPilih2() {
		return pilih2;
	}

	public void setPilih2(String pilih2) {
		this.pilih2 = pilih2;
	}

	public Map<String, String> getSelect1() {
		return select1;
	}

	public void setSelect1(Map<String, String> select1) {
		this.select1 = select1;
	}

	public ArrayList<String> getSelect2() {
		return select2;
	}

	public void setSelect2(ArrayList<String> select2) {
		this.select2 = select2;
	}

	public Map<String, String> getTampung() {
		return tampung;
	}

	public void setTampung(Map<String, String> tampung) {
		this.tampung = tampung;
	}

	public Map<String, Map<String, String>> getData() {
		return data;
	}

	public void setData(Map<String, Map<String, String>> data) {
		this.data = data;
	}

	public Map<String, String> getTahun() {
		return tahun;
	}

	public void setTahun(Map<String, String> tahun) {
		this.tahun = tahun;
	}

	public Map<String, String> getBulan() {
		return bulan;
	}

	public void setBulan(Map<String, String> bulan) {
		this.bulan = bulan;
	}

	public Map<String, LaporanTahun> getMapTahun() {
		return mapTahun;
	}

	public void setMapTahun(Map<String, LaporanTahun> mapTahun) {
		this.mapTahun = mapTahun;
	}

	public Map<String, LaporanBulan> getMapBulan() {
		return mapBulan;
	}

	public void setMapBulan(Map<String, LaporanBulan> mapBulan) {
		this.mapBulan = mapBulan;
	}

	public void handleChange() {
		if (getPilih1() != null && !getPilih1().equals("")) {
			setTampung(getData().get(getPilih1()));
		} else {
			setTampung(new HashMap<String, String>());
		}

		setSelect2(new ArrayList<String>());
		for (int i = 1; i <= getTampung().size(); i++) {
			getSelect2().add(getTampung().get("" + i));
		}
	}

	public void createReportData() {
		int in = 0;
		int out = 0;
		
		int pendapatanJan14 = 0;
		int pengeluaranJan14 = 0;

		int pendapatanFeb14 = 0;
		int pengeluaranFeb14 = 0;

		int pendapatanMar14 = 0;
		int pengeluaranMar14 = 0;

		int pendapatanApr14 = 0;
		int pengeluaranApr14 = 0;

		int i = 1;
		while (i <= 31) {
			in = 13;
			out = 10;
			String index = "Januari 2014 " + i;
			mapBulan.put(index, new LaporanBulan(String.valueOf(i), in, out));
			pendapatanJan14 += in;
			pengeluaranJan14 += out;
			i++;
		}

		i = 1;
		while (i <= 28) {
			in = 13;
			out = 10;
			String index = "Februari 2014 " + (i);
			mapBulan.put(index, new LaporanBulan(String.valueOf(i), in, out));
			pendapatanFeb14 += in;
			pengeluaranFeb14 += out;
			i++;
		}

		i = 1;
		while (i <= 31) {
			in = 15;
			out = 8;
			String index = "Maret 2014 " + (i);
			mapBulan.put(index, new LaporanBulan(String.valueOf(i), in, out));
			pendapatanMar14 += in;
			pengeluaranMar14 += out;
			i++;
		}

		i = 1;
		while (i <= 30) {
			in = 11;
			out = 10;
			String index = "April 2014 " + (i);
			mapBulan.put(index, new LaporanBulan(String.valueOf(i), in, out));
			pendapatanApr14 += in;
			pengeluaranApr14 += out;
			i++;
		}

		LaporanTahun lapThn20131 = new LaporanTahun("Januari", 360, 260);
		LaporanTahun lapThn20132 = new LaporanTahun("Februari", 280, 190);
		LaporanTahun lapThn20133 = new LaporanTahun("Maret", 350, 320);
		LaporanTahun lapThn20134 = new LaporanTahun("April", 230, 360);
		LaporanTahun lapThn20135 = new LaporanTahun("Mei", 400, 200);
		LaporanTahun lapThn20136 = new LaporanTahun("Juni", 320, 460);
		LaporanTahun lapThn20137 = new LaporanTahun("Juli", 200, 360);
		LaporanTahun lapThn20138 = new LaporanTahun("Agustus", 280, 290);
		LaporanTahun lapThn20139 = new LaporanTahun("September", 350, 220);
		LaporanTahun lapThn201310 = new LaporanTahun("Oktober", 200, 160);
		LaporanTahun lapThn201311 = new LaporanTahun("November", 280, 200);
		LaporanTahun lapThn201312 = new LaporanTahun("Desember", 300, 320);

	

		LaporanTahun lapThn20141 = new LaporanTahun("Januari", pendapatanJan14,
				pengeluaranJan14);
		LaporanTahun lapThn20142 = new LaporanTahun("Februari",
				pendapatanFeb14, pengeluaranFeb14);
		LaporanTahun lapThn20143 = new LaporanTahun("Maret", pendapatanMar14,
				pengeluaranMar14);
		LaporanTahun lapThn20144 = new LaporanTahun("April", pendapatanApr14,
				pengeluaranApr14);

		mapTahun.put("Tahun 2013 1", lapThn20131);
		mapTahun.put("Tahun 2013 2", lapThn20132);
		mapTahun.put("Tahun 2013 3", lapThn20133);
		mapTahun.put("Tahun 2013 4", lapThn20134);
		mapTahun.put("Tahun 2013 5", lapThn20135);
		mapTahun.put("Tahun 2013 6", lapThn20136);
		mapTahun.put("Tahun 2013 7", lapThn20137);
		mapTahun.put("Tahun 2013 8", lapThn20138);
		mapTahun.put("Tahun 2013 9", lapThn20139);
		mapTahun.put("Tahun 2013 10", lapThn201310);
		mapTahun.put("Tahun 2013 11", lapThn201311);
		mapTahun.put("Tahun 2013 12", lapThn201312);

		mapTahun.put("Tahun 2014 1", lapThn20141);
		mapTahun.put("Tahun 2014 2", lapThn20142);
		mapTahun.put("Tahun 2014 3", lapThn20143);
		mapTahun.put("Tahun 2014 4", lapThn20144);
	}

	public void createMap() {
		select1.put("Bulan", "Bulan");
		select1.put("Tahun", "Tahun");

		tahun.put("1", "Tahun 2013");
		tahun.put("2", "Tahun 2014");

		bulan.put("1", "Januari 2014");
		bulan.put("2", "Februari 2014");
		bulan.put("3", "Maret 2014");
		bulan.put("4", "April 2014");

		data.put("Tahun", tahun);
		data.put("Bulan", bulan);
	}

	// Source Grafik Laporan

	private CartesianChartModel reportGraph;

	public void setReportGraph(CartesianChartModel reportGraph) {
		this.reportGraph = reportGraph;
	}

	public CartesianChartModel getReportGraph() {
		return reportGraph;
	}

	public void handleGraphChange() {
		if (getPilih1().equalsIgnoreCase("Tahun")) {
			setMax(500);

			reportGraph = new CartesianChartModel();

			ChartSeries income = new ChartSeries();
			income.setLabel("Income");

			ChartSeries outcome = new ChartSeries();
			outcome.setLabel("Outcome");

			LaporanTahun lapThn = new LaporanTahun();

			for (int i = 1; i <= 12; i++) {
				lapThn = mapTahun.get(pilih2 + " " + i);
				if (lapThn != null) {
					income.set(lapThn.getBulan(), lapThn.getPendapatan());
					outcome.set(lapThn.getBulan(), lapThn.getPengeluaran());
				}
			}

			reportGraph.addSeries(income);
			reportGraph.addSeries(outcome);
		} else if (getPilih1().equalsIgnoreCase("Bulan")) {
			setMax(20);

			reportGraph = new CartesianChartModel();

			ChartSeries income = new ChartSeries();
			income.setLabel("Income");

			ChartSeries outcome = new ChartSeries();
			outcome.setLabel("Outcome");

			LaporanBulan lapBln = new LaporanBulan();

			for (int i = 1; i <= 31; i++) {
				try {
					lapBln = mapBulan.get(getPilih2() + " " + i);
					income.set(lapBln.getTanggal(), lapBln.getPendapatan());
					outcome.set(lapBln.getTanggal(), lapBln.getPengeluaran());
				} catch (NullPointerException e) {}
			}
			reportGraph.addSeries(income);
			reportGraph.addSeries(outcome);
		}
	}

	private void createGraph() {
		reportGraph = new CartesianChartModel();

		ChartSeries income = new ChartSeries();
		income.setLabel("Income");

		ChartSeries outcome = new ChartSeries();
		outcome.setLabel("Outcome");

		income.set("", null);
		outcome.set("", null);

		reportGraph.addSeries(income);
		reportGraph.addSeries(outcome);
	}
}
