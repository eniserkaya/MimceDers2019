package com.eniserkaya.loginpanel;

public class CagriKaydi {

    private String arayanAdi;
    private String arayanNo;
    private String aramaZamani;
    private String aramaTipi;
    private int callDuration;

    public CagriKaydi() {
    }

    public CagriKaydi(String arayanAdi, String arayanNo, String aramaZamani, String aramaTipi, int callDuration) {
        this.arayanAdi = arayanAdi;
        this.arayanNo = arayanNo;
        this.aramaZamani = aramaZamani;
        this.aramaTipi = aramaTipi;
        this.callDuration = callDuration;
    }

    public String getArayanAdi() {
        return arayanAdi;
    }

    public void setArayanAdi(String arayanAdi) {
        this.arayanAdi = arayanAdi;
    }

    public String getArayanNo() {
        return arayanNo;
    }

    public void setArayanNo(String arayanNo) {
        this.arayanNo = arayanNo;
    }

    public String getAramaZamani() {
        return aramaZamani;
    }

    public void setAramaZamani(String aramaZamani) {
        this.aramaZamani = aramaZamani;
    }

    public String getAramaTipi() {
        return aramaTipi;
    }

    public void setAramaTipi(String aramaTipi) {
        this.aramaTipi = aramaTipi;
    }

    public int getCallDuration() {
        return callDuration;
    }

    public void setCallDuration(int callDuration) {
        this.callDuration = callDuration;
    }
}
