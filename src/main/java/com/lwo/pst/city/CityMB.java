package com.lwo.pst.city;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;
import org.omnifaces.cdi.ViewScoped;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.event.data.FilterEvent;

/**
 *
 * @author drozdov_d
 */
@Named
@ViewScoped
public class CityMB implements Serializable {
    
    @Inject CityService  city_service;
    
    private boolean exist = false;
    private Date lastModified;
    private List<City> cities         = new ArrayList<>();
    private List<City> filteredCities = new ArrayList<>();
    
    @PostConstruct
    public void onInit() {
       this.cities          = city_service.getListCity();
       this.filteredCities  = this.cities;
    }
    
    
    public boolean isExist() {
        return exist;
    }

    public void setExist(boolean exist) {
        this.exist = exist;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setFilteredCities( List<City> filteredCities ) {
        System.out.println("--> " + filteredCities );
        this.filteredCities = filteredCities;
    }

    public List<City> getFilteredCities() {
        return filteredCities;
    }
    
    public void filterListener(FilterEvent filterEvent){
        DataTable table = (DataTable) filterEvent.getSource();
        System.out.println("-----> " + table.getGlobalFilter());
        String val = (String) filterEvent.getFilters().get("globalFilter");
        System.out.println("-----> " + filterEvent.getFilters().get("globalFilter"));
        this.filteredCities = city_service.getListCity( val );
        this.cities         = city_service.getListCity( val );    
    }
    
}
