package com.example.delfinv2.Model;

public class Pregunta {

    private int id;
    private String pregunta_es,pregunta_en,pregunta_fr,respuesta_es,respuesta_en,respuesta_fr;

    public Pregunta() {
    }

    public Pregunta(int id, String pregunta_es, String pregunta_en, String pregunta_fr, String respuesta_es, String respuesta_en, String respuesta_fr) {
        this.id = id;
        this.pregunta_es = pregunta_es;
        this.pregunta_en = pregunta_en;
        this.pregunta_fr = pregunta_fr;
        this.respuesta_es = respuesta_es;
        this.respuesta_en = respuesta_en;
        this.respuesta_fr = respuesta_fr;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPregunta_es() {
        return pregunta_es;
    }

    public void setPregunta_es(String pregunta_es) {
        this.pregunta_es = pregunta_es;
    }

    public String getPregunta_en() {
        return pregunta_en;
    }

    public void setPregunta_en(String pregunta_en) {
        this.pregunta_en = pregunta_en;
    }

    public String getPregunta_fr() {
        return pregunta_fr;
    }

    public void setPregunta_fr(String pregunta_fr) {
        this.pregunta_fr = pregunta_fr;
    }

    public String getRespuesta_es() {
        return respuesta_es;
    }

    public void setRespuesta_es(String respuesta_es) {
        this.respuesta_es = respuesta_es;
    }

    public String getRespuesta_en() {
        return respuesta_en;
    }

    public void setRespuesta_en(String respuesta_en) {
        this.respuesta_en = respuesta_en;
    }

    public String getRespuesta_fr() {
        return respuesta_fr;
    }

    public void setRespuesta_fr(String respuesta_fr) {
        this.respuesta_fr = respuesta_fr;
    }
}
