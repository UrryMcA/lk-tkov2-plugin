package com.groupstp.rtneo.plugin;

import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.sys.AppContext;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.UiComponents;
import com.haulmont.cuba.gui.screen.Screen;


public interface PlugInRunnable {

    public String execute();

    String execute(AppContext ac, DataManager dataManager, UiComponents uiComponents,
                   Notifications notifications, Screen screen, ScreenBuilders screenBuilders);

}
