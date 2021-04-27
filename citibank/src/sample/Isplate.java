package sample;

public class Isplate {

    int brojIsplacenihMK;
    int maxIsplacenihMK;
    int sumaIsplacenihMK;
    int prosjekIsplacenihMK;

    public Isplate (int broj, int max, int suma, int prosjek){

        this.brojIsplacenihMK = broj;
        this.maxIsplacenihMK = max;
        this.sumaIsplacenihMK = suma;
        this.prosjekIsplacenihMK = prosjek;
    }

    public int getBrojIsplacenihMK() {
        return brojIsplacenihMK;
    }

    public void setBrojIsplacenihMK(int brojIsplacenihMK) {
        this.brojIsplacenihMK = brojIsplacenihMK;
    }

    public int getMaxIsplacenihMK() {
        return maxIsplacenihMK;
    }

    public void setMaxIsplacenihMK(int maxIsplacenihMK) {
        this.maxIsplacenihMK = maxIsplacenihMK;
    }

    public int getSumaIsplacenihMK() {
        return sumaIsplacenihMK;
    }

    public void setSumaIsplacenihMK(int sumaIsplacenihMK) {
        this.sumaIsplacenihMK = sumaIsplacenihMK;
    }

    public int getProsjekIsplacenihMK() {
        return prosjekIsplacenihMK;
    }

    public void setProsjekIsplacenihMK(int prosjekIsplacenihMK) {
        this.prosjekIsplacenihMK = prosjekIsplacenihMK;
    }

    @Override
    public String toString() {
        String string = "" + brojIsplacenihMK + ", " + maxIsplacenihMK + ", " + sumaIsplacenihMK + ", " +
                "" + prosjekIsplacenihMK + "";
        return string;
    }

}
