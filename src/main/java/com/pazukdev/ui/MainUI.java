package com.pazukdev.ui;

import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;

import com.pazukdev.services.ServiceProvider;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.EnableVaadin;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.server.SpringVaadinServlet;
import com.vaadin.ui.*;

import com.vaadin.ui.themes.ValoTheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.ContextLoaderListener;

@Theme("mytheme")
//@Title("Hotels")
@SuppressWarnings("serial")
@SpringUI
public class MainUI extends UI {

    @WebListener
    public static class MyContextLoaderListener extends ContextLoaderListener {
    }

    @Configuration
    @EnableVaadin
    public static class MyConfiguration {
        @Bean
        public static ServiceProvider getEntityService() {
            return new ServiceProvider();
        }
    }

    private Navigator navigator;

    private VerticalLayout pcScreenLayout = new VerticalLayout();
    private VerticalLayout mobileScreenLayout = new VerticalLayout();
    private HorizontalLayout actionBar = new HorizontalLayout();
    private HorizontalLayout actionBarTitleLayout = new HorizontalLayout();
    private HorizontalLayout content = new HorizontalLayout();

    private MainLayout mainLayout;
    private BearingsLayout bearingsLayout = new BearingsLayout();

    private Label pageTitle = new Label("Bearings usage");
    private Button backButton = new Button();

    @Override
    protected void init(VaadinRequest vaadinRequest) {

        actionBarTitleLayout.addComponent(pageTitle);
        actionBarTitleLayout.setMargin(false);
        actionBarTitleLayout.setSizeFull();
        actionBarTitleLayout.setComponentAlignment(pageTitle, Alignment.MIDDLE_LEFT);
        actionBar.setStyleName("body");
        actionBar.addComponents(backButton, actionBarTitleLayout);
        actionBar.setComponentAlignment(backButton, Alignment.MIDDLE_LEFT);
        actionBar.setStyleName("actionBar");
        actionBar.setHeight("100px");
        actionBar.setWidth("100%");
        actionBar.setMargin(false);

        content.setMargin(false);
        content.setSpacing(false);
        content.setWidth(String.valueOf("480px"));
        content.setHeight(String.valueOf(bearingsLayout.getBearingsGrid().getHeight()));
        content.setStyleName("content");

        mobileScreenLayout.addComponents(actionBar, content);
        mobileScreenLayout.setComponentAlignment(content, Alignment.TOP_CENTER);
        mobileScreenLayout.setWidth(String.valueOf(bearingsLayout.getBearingsGrid().getWidth()));
        //mobileScreenLayout.setWidth(String.valueOf("500px"));
        mobileScreenLayout.setMargin(false);
        mobileScreenLayout.setSpacing(false);

        pcScreenLayout.addComponent(mobileScreenLayout);
        pcScreenLayout.setComponentAlignment(mobileScreenLayout, Alignment.TOP_CENTER);
        pcScreenLayout.setStyleName("body");
        pcScreenLayout.setSizeFull();

        setContent(pcScreenLayout);

        navigator = new Navigator(this, content);
        mainLayout = new MainLayout(navigator, backButton);
        setButtons();
        navigator.addView("main", mainLayout);
        navigator.addView("bearings", bearingsLayout);
        navigator.navigateTo("main");
    }


    private void setButtons() {
        // back button
        backButton.addStyleNames(ValoTheme.BUTTON_HUGE, ValoTheme.BUTTON_BORDERLESS);
        backButton.setId("back_button");
        backButton.setEnabled(false);
        backButton.setIcon(null);
        backButton.setDescription("Back");
        backButton.addClickListener(event -> {
            backButton.setEnabled(false);
            backButton.setIcon(null);
            navigator.navigateTo("main");
        });
    }


    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MainUI.class, productionMode = false)
    public static class MyUIServlet extends SpringVaadinServlet {}

}