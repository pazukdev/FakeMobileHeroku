package com.pazukdev.ui;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class MainLayout extends VerticalLayout implements View {

    Button bearingsButton = new Button("Bearings");

    public MainLayout(Navigator navigator, Button backButton) {
        setSizeFull();
        addComponents(bearingsButton);
        setComponentAlignment(bearingsButton, Alignment.MIDDLE_CENTER);

        bearingsButton.addStyleNames(ValoTheme.BUTTON_FRIENDLY, ValoTheme.BUTTON_HUGE);
        bearingsButton.addClickListener(event -> {
            backButton.setEnabled(true);
            backButton.setIcon(VaadinIcons.ARROW_LEFT);
            backButton.setVisible(true);
            navigator.navigateTo("bearings");
        });
    }

}
