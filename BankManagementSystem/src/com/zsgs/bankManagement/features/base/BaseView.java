package com.zsgs.bankManagement.features.base;

public abstract class BaseView {

    protected void exitApp(){
        System.out.println("--------Thank you for using SBI Bank !------");
        System.exit(0);
    }
    public void showMessage(String message) {
        System.out.println(message);
    }
    public void errorMassage(String error) {
		System.out.println(error);
	}
}
