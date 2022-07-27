package com.example.damurafiki.Model_class;

import java.io.Serializable;

public class CardListModelClass implements Serializable {

    String blood_group;
    String person_name;
    String distance;
    String uhitaji;
    String zilizopatikana;
    String waliotayari;
    String share_count;

    public CardListModelClass() {
    }


    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    String phone_number;



    public String getBlood_group() {
        return blood_group;
    }

    public void setBlood_group(String blood_group) {
        this.blood_group = blood_group;
    }

    public String getPerson_name() {
        return person_name;
    }

    public void setPerson_name(String person_name) {
        this.person_name = person_name;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getUhitaji() {
        return uhitaji;
    }

    public void setUhitaji(String uhitaji) {
        this.uhitaji = uhitaji;
    }

    public String getZilizopatikana() {
        return zilizopatikana;
    }

    public void setZilizopatikana(String zilizopatikana) {
        this.zilizopatikana = zilizopatikana;
    }

    public String getWaliotayari() {
        return waliotayari;
    }

    public void setWaliotayari(String waliotayari) {
        this.waliotayari = waliotayari;
    }

    public String getShare_count() {
        return share_count;
    }

    public void setShare_count(String share_count) {
        this.share_count = share_count;
    }
}
