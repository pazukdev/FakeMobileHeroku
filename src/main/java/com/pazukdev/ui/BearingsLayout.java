package com.pazukdev.ui;

import com.pazukdev.entities.OldBearing;
import com.pazukdev.services.OldBearingService;

import com.vaadin.navigator.View;
import com.vaadin.shared.data.sort.SortDirection;
import com.vaadin.ui.*;

import java.util.List;


public class BearingsLayout extends VerticalLayout implements View {

    private OldBearingService oldBearingService = OldBearingService.getInstance();
    private Grid<OldBearing> bearingsGrid =new Grid<>(OldBearing.class);
    //private Grid<Bearing> bearingsGrid =new Grid<>();


    public BearingsLayout() {
        setGrid();
        updateBearingsList();
        addComponent(bearingsGrid);
        setComponentAlignment(bearingsGrid, Alignment.MIDDLE_CENTER);
        setMargin(false);
        setSpacing(false);
    }


    private void setGrid() {
        bearingsGrid.setSelectionMode(Grid.SelectionMode.NONE);
        bearingsGrid.setWidth("480px");
        bearingsGrid.setHeight("640px");
        bearingsGrid.setBodyRowHeight(34);
        bearingsGrid.sort(bearingsGrid.getColumn("majorLocation"), SortDirection.ASCENDING);
        bearingsGrid.setColumns(
                //"id",
                "numberOfOriginal",
                "type",
                "majorLocation",
                "quantity"
        );
        bearingsGrid.getColumn("quantity").setCaption("Pcs");
    }


    public void updateBearingsList() {
        List<OldBearing> oldBearings = oldBearingService.findAll();
        bearingsGrid.setItems(oldBearings);
    }


    public Grid<OldBearing> getBearingsGrid() {
        return bearingsGrid;
    }

}


























