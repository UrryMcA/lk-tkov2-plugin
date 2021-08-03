package com.groupstp.rtneo.plugin;

import com.groupstp.rtneo.service.AccrualServiceBean;
import com.groupstp.rtneo.service.SmsService;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.sys.AppContext;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.ScreenBuilders;

import com.haulmont.cuba.gui.UiComponents;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.screen.Screen;
import com.haulmont.cuba.web.gui.components.WebButton;
import com.haulmont.cuba.web.gui.components.WebTextField;


public class Executor implements PlugInRunnable {

    @Override
    public String execute() {
        return null;
    }

    TextField phoneField;
    TextField msgField;
    TextField fromPhoneField;
    TextField MsgIdField;

    @Override
    public String execute(AppContext ac, DataManager dataManager, UiComponents uiComponents,
                          Notifications notifications, Screen screen, ScreenBuilders screenBuilders) {


        //Object sb = AppBeans.get("AccrualService");

        //AccrualServiceBean asb = AppBeans.get(AccrualServiceBean.class);
       /* try {
            //Class cl    = Class.forName("EmptyScreen");
            //screenBuilders.screen(null).withScreenClass(cl);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        */
        Window window = screen.getWindow();
        window.setCaption("Проверка СНИЛС");

        window.setWidth("600pt");
        window.setHeight("600pt");

        BoxLayout vbl = uiComponents.create(VBoxLayout.class);
        vbl.setMargin(true);
        window.add(vbl);

        phoneField = new WebTextField();
        phoneField.setCaption("СНИЛС ");
        vbl.add(phoneField);

        Button btn = new WebButton();
        btn.setVisible(true);
        btn.setCaption("Проверить");
        btn.addClickListener(clickEvent -> onClickButton(notifications));
        vbl.add(btn);
        /*
        Window window = screen.getWindow();
        window.setCaption("SMS service test modal dialog");

        window.setWidth("600pt");
        window.setHeight("600pt");

        BoxLayout vbl = uiComponents.create(VBoxLayout.class);
        vbl.setMargin(true);

        phoneField = new WebTextField();
        phoneField.setCaption("Phone number ");

        msgField = new WebTextField();
        msgField.setCaption("Message ");

        fromPhoneField = new WebTextField();
        fromPhoneField.setCaption("From Phone ");

        MsgIdField = new WebTextField();
        MsgIdField.setCaption("Msg number");

        Button btn = new WebButton();
        btn.setVisible(true);
        btn.setCaption("Send message");
        btn.addClickListener(clickEvent -> onClickButton(notifications));

        Button btn1 = new WebButton();
        btn1.setVisible(true);
        btn1.setCaption("TEST BUTTON 1");
        btn1.addClickListener(clickEvent -> onClickButton1(notifications));

        vbl.add(phoneField);
        vbl.add(msgField);
        //vbl.add(fromPhoneField);
        //vbl.add(MsgIdField);

        vbl.add(btn);
        //vbl.add(btn1);

        window.add(vbl);

        return "version=0";
*/
        return "";

    }

    private void onClickButton(Notifications notifications) {

       /* SmsService sm = AppBeans.get(SmsService.NAME);

        String phoneNumber  =  (String)phoneField.getValue();
        String message      =  (String)msgField.getValue();
        String fromPhone    =  (String)fromPhoneField.getValue();
       // String MsgId        =  (String)MsgIdField.getValue();

        int res = sm.sendSms(phoneNumber,message,"",0);

        if(res==0)
            notifications.create().withCaption("Message have been sended!").show();
        else
            notifications.create().withCaption("Message not have been sended!").show();

        */
        String phoneNumber  =  (String)phoneField.getValue();

        if(checkSnils(phoneNumber))
            notifications.create().withCaption("OK").show();
        else
            notifications.create().withCaption("ERROR").show();
        //String formatted_phone = phoneNumber.replaceFirst(message, "()--");
        //notifications.create().withCaption(formatted_phone).show();
/*
        java.text.MessageFormat phoneMsgFmt=new java.text.MessageFormat("+7({0})-{1}-{2}-{3}");
        //suposing a grouping of 3-3-4
        String[] phoneNumArr={phoneNumber.substring(0, 3),
                phoneNumber.substring(3,6),
                phoneNumber.substring(6,8),
                phoneNumber.substring(8,10)};

        notifications.create().withCaption(phoneMsgFmt.format(phoneNumArr)).show();
       // System.out.println(phoneMsgFmt.format(phoneNumArr));
       /*
 */
    }

    public boolean checkSnils(String snils){

        snils = snils.replace(" ", "");
        snils = snils.replace("-", "");

        if(snils.length()!=11)
            return false;

        String checksumString = snils.substring(9,11);
        Integer checkSumInt = new Integer(checksumString);

        Integer sum = 0;

        for(int i=0;i<=9;i++) {
            String s = snils.substring(i,i+1);
            Integer res = new Integer(s) * (9-i);
            System.out.printf("%s %s\n",s,res.toString());

            sum = sum + res;
        }

        if(sum < 100)
        {
            if(checkSumInt.equals(sum)) return true;
            else return false;
        }

        while(sum>101)
            sum = sum % 101;

        if(sum.equals(100)||sum.equals(101))
            sum = 0;

        if(sum.equals(checkSumInt))
            return true;

        return false;
    }

    private void onClickButton1(Notifications notifications) {
        notifications.create().withCaption("Hello AlL 123!").show();
    }
}
