package com.example.delfinv2.Model;

public class Categoria {
    private int id;
    private String nombres_es,nombres_en,nombres_fr;

    public Categoria() {
    }

    public Categoria(int id, String nombres_es, String nombres_en, String nombres_fr) {
        this.id = id;
        this.nombres_es = nombres_es;
        this.nombres_en = nombres_en;
        this.nombres_fr = nombres_fr;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombres_es() {
        return nombres_es;
    }

    public void setNombres_es(String nombres_es) {
        this.nombres_es = nombres_es;
    }

    public String getNombres_en() {
        return nombres_en;
    }

    public void setNombres_en(String nombres_en) {
        this.nombres_en = nombres_en;
    }

    public String getNombres_fr() {
        return nombres_fr;
    }

    public void setNombres_fr(String nombres_fr) {
        this.nombres_fr = nombres_fr;
    }
}
