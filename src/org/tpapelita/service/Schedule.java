package org.tpapelita.service;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
import org.tpapelita.pojo.service.Event;

@ManagedBean
@SessionScoped
public class Schedule implements Serializable {  
	  
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ScheduleModel eventModel;  
      
    private ScheduleEvent event = new DefaultScheduleEvent();  
  
    public Schedule() {  
        eventModel = new DefaultScheduleModel();  
        Event event1 = new Event("Pengajian", setTanggal(2014, Calendar.MAY, 21), setTanggal(2014, Calendar.MAY, 21), setWaktu(Calendar.HOUR_OF_DAY, 19), setWaktu(Calendar.HOUR_OF_DAY, 21), setWaktu(Calendar.MINUTE, 0), setWaktu(Calendar.MINUTE, 0));
        Event event2 = new Event("Sunatan Masal", setTanggal(2014, Calendar.MAY, 9), setTanggal(2014, Calendar.MAY, 10), setWaktu(Calendar.HOUR_OF_DAY, 9), setWaktu(Calendar.HOUR_OF_DAY, 15), setWaktu(Calendar.MINUTE, 0), setWaktu(Calendar.MINUTE, 0));
        Event event3 = new Event("Ceramah Ustadz", setTanggal(2014, Calendar.MAY, 23), setTanggal(2014, Calendar.MAY, 23), setWaktu(Calendar.HOUR_OF_DAY, 11), setWaktu(Calendar.HOUR_OF_DAY, 13), setWaktu(Calendar.MINUTE, 0), setWaktu(Calendar.MINUTE, 0));
        
        
        eventModel.addEvent(new DefaultScheduleEvent(event1.getNamaEvent(), createTimeEvent(event1.getTglMulai(), event1.getJamMulai(), event1.getMenitMulai()), createTimeEvent(event1.getTglSelesai(), event1.getJamSelesai(), event1.getMenitSelesai())));
        eventModel.addEvent(new DefaultScheduleEvent(event2.getNamaEvent(), createTimeEvent(event2.getTglMulai(), event2.getJamMulai(), event2.getMenitMulai()), createTimeEvent(event2.getTglSelesai(), event2.getJamSelesai(), event2.getMenitSelesai())));
        eventModel.addEvent(new DefaultScheduleEvent(event3.getNamaEvent(), createTimeEvent(event3.getTglMulai(), event3.getJamMulai(), event3.getMenitMulai()), createTimeEvent(event3.getTglSelesai(), event3.getJamSelesai(), event3.getMenitSelesai())));
    }  
      
    public Calendar setTanggal(int tahun, int bulan, int tanggal){
    	Calendar calendarTgl = Calendar.getInstance();
    	calendarTgl.set(tahun, bulan, tanggal);
    	return calendarTgl;
    }
    
    public Calendar setWaktu(int field, int value){
    	Calendar calendarJam = Calendar.getInstance();
    	calendarJam.set(field, value);
    	return calendarJam;
    }
    
    public Date createTimeEvent(Calendar tgl, Calendar jam, Calendar menit){
    	Calendar waktu = Calendar.getInstance();
    	waktu.set(Calendar.DATE, tgl.get(Calendar.DATE));
    	waktu.set(Calendar.MONTH, tgl.get(Calendar.MONTH));
    	waktu.set(Calendar.YEAR, tgl.get(Calendar.YEAR));
        waktu.set(Calendar.HOUR_OF_DAY, jam.get(Calendar.HOUR_OF_DAY));  
        waktu.set(Calendar.MINUTE, menit.get(Calendar.MINUTE));
          
        return waktu.getTime();  
    }
    
    public Date getRandomDate(Date base) {  
        Calendar date = Calendar.getInstance();  
        date.setTime(base);  
        date.add(Calendar.DATE, ((int) (Math.random()*30)) + 1);    //set random day of month  
          
        return date.getTime();  
    }  
      
    public Date getInitialDate() {  
        Calendar calendar = Calendar.getInstance();  
        calendar.set(calendar.get(Calendar.YEAR), Calendar.FEBRUARY, calendar.get(Calendar.DATE), 0, 0, 0);  
          
        return calendar.getTime();  
    }  
      
    public ScheduleModel getEventModel() {  
        return eventModel;  
    }  
      
    public ScheduleEvent getEvent() {  
        return event;  
    }  
  
    public void setEvent(ScheduleEvent event) {  
        this.event = event;  
    }  
      
    public void addEvent(ActionEvent actionEvent) {  
        if(event.getId() == null)  
            eventModel.addEvent(event);  
        else  
            eventModel.updateEvent(event);  
          
        event = new DefaultScheduleEvent();  
    }  
      
    public void onEventSelect(SelectEvent selectEvent) {  
        event = (ScheduleEvent) selectEvent.getObject();  
    }  
      
    public void onDateSelect(SelectEvent selectEvent) {  
        event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(), (Date) selectEvent.getObject());  
    }  
      
    public void onEventMove(ScheduleEntryMoveEvent event) {  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event moved", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());  
          
        addMessage(message);  
    }  
      
    public void onEventResize(ScheduleEntryResizeEvent event) {  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event resized", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());  
          
        addMessage(message);  
    }  
      
    private void addMessage(FacesMessage message) {  
        FacesContext.getCurrentInstance().addMessage(null, message);  
    }  
}  