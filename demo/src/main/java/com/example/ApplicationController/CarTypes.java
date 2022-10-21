package com.example.ApplicationController;

public enum CarTypes {
	PRIME(400,"Prime"),AUTO(70,"Auto"),SEDAN(600,"Sedan"),MINI(150,"Mini");

    int rangeOfAmount;
    String name;
    CarTypes(int rangeOfAmount,String name){
        this.rangeOfAmount=rangeOfAmount;
        this.name = name;
    }
}
