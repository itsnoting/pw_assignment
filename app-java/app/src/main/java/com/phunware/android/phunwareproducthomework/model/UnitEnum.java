package com.phunware.android.phunwareproducthomework.model;

public enum UnitEnum {
    farenheit("imperial"),
    celsius("metric");

    UnitEnum(String unitSystem) {
        this.unitSystem = unitSystem;
    }
    private final String unitSystem;
    public String getUnitSystem() {
        return unitSystem;
    }

}
