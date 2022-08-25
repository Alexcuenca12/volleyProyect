package com.alexis.siavi.volleyproyect.model;

public class Country {

    private String country;
    private String slug;
    private String ISO2;


    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getISO2() {
        return ISO2;
    }

    public void setISO2(String ISO2) {
        this.ISO2 = ISO2;
    }

    @Override
    public String toString() {
        return
                "Country: " + country +
                ", \nSlug: " + slug +
                ", \nISO2: " + ISO2+"\n";
    }
}

