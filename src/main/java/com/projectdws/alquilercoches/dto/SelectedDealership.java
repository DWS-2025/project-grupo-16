package com.projectdws.alquilercoches.dto;

import com.projectdws.alquilercoches.models.Dealership;

public class SelectedDealership {
    private Dealership dealership;
    private boolean isSelected;

    public SelectedDealership(Dealership dealership, boolean isSelected){
        this.dealership = dealership;
        this.isSelected = isSelected;
    }
}
