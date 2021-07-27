package com.example.delfinv2.Model;

public class Eventos {
    private int id;
    private String nombre_es,nombre_en,nombre_fr;
    private String descripcion_es,descripcion_en,descripcion_fr;
    private String requisitos_es,requisitos_en,requisitos_fr;
    private String fecha;
    private String hora_inicio,hora_fin,cupo,status,imagen;
    private int fk_tipo,fk_cat;
    private String tipo_es,tipo_en,tipo_fr;
    private String cat_es,cat_en,cat_fr;
    private String fk_sala;
    private int fk_sala_id, fk_ubi;
    private String fk_ubi_name,fk_sala_virt;
    private int fk_sala_virt_id;
    private String link, fk_ponente;

    public Eventos() {
    }

    public Eventos(int id, String nombre_es, String nombre_en, String nombre_fr, String descripcion_es, String descripcion_en, String descripcion_fr, String requisitos_es, String requisitos_en, String requisitos_fr, String fecha, String hora_inicio, String hora_fin, String cupo, String status, String imagen, int fk_tipo, int fk_cat, String tipo_es, String tipo_en, String tipo_fr, String cat_es, String cat_en, String cat_fr, String fk_sala, int fk_sala_id, int fk_ubi, String fk_ubi_name, String fk_sala_virt, int fk_sala_virt_id, String link, String fk_ponente) {
        this.id = id;
        this.nombre_es = nombre_es;
        this.nombre_en = nombre_en;
        this.nombre_fr = nombre_fr;
        this.descripcion_es = descripcion_es;
        this.descripcion_en = descripcion_en;
        this.descripcion_fr = descripcion_fr;
        this.requisitos_es = requisitos_es;
        this.requisitos_en = requisitos_en;
        this.requisitos_fr = requisitos_fr;
        this.fecha = fecha;
        this.hora_inicio = hora_inicio;
        this.hora_fin = hora_fin;
        this.cupo = cupo;
        this.status = status;
        this.imagen = imagen;
        this.fk_tipo = fk_tipo;
        this.fk_cat = fk_cat;
        this.tipo_es = tipo_es;
        this.tipo_en = tipo_en;
        this.tipo_fr = tipo_fr;
        this.cat_es = cat_es;
        this.cat_en = cat_en;
        this.cat_fr = cat_fr;
        this.fk_sala = fk_sala;
        this.fk_sala_id = fk_sala_id;
        this.fk_ubi = fk_ubi;
        this.fk_ubi_name = fk_ubi_name;
        this.fk_sala_virt = fk_sala_virt;
        this.fk_sala_virt_id = fk_sala_virt_id;
        this.link = link;
        this.fk_ponente = fk_ponente;
    }

    public String getHora_inicio() {
        return hora_inicio;
    }

    public void setHora_inicio(String hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public String getHora_fin() {
        return hora_fin;
    }

    public void setHora_fin(String hora_fin) {
        this.hora_fin = hora_fin;
    }

    public String getCupo() {
        return cupo;
    }

    public void setCupo(String cupo) {
        this.cupo = cupo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getFk_tipo() {
        return fk_tipo;
    }

    public void setFk_tipo(int fk_tipo) {
        this.fk_tipo = fk_tipo;
    }

    public int getFk_cat() {
        return fk_cat;
    }

    public void setFk_cat(int fk_cat) {
        this.fk_cat = fk_cat;
    }

    public String getTipo_es() {
        return tipo_es;
    }

    public void setTipo_es(String tipo_es) {
        this.tipo_es = tipo_es;
    }

    public String getTipo_en() {
        return tipo_en;
    }

    public void setTipo_en(String tipo_en) {
        this.tipo_en = tipo_en;
    }

    public String getTipo_fr() {
        return tipo_fr;
    }

    public void setTipo_fr(String tipo_fr) {
        this.tipo_fr = tipo_fr;
    }

    public String getCat_es() {
        return cat_es;
    }

    public void setCat_es(String cat_es) {
        this.cat_es = cat_es;
    }

    public String getCat_en() {
        return cat_en;
    }

    public void setCat_en(String cat_en) {
        this.cat_en = cat_en;
    }

    public String getCat_fr() {
        return cat_fr;
    }

    public void setCat_fr(String cat_fr) {
        this.cat_fr = cat_fr;
    }

    public String getFk_sala() {
        return fk_sala;
    }

    public void setFk_sala(String fk_sala) {
        this.fk_sala = fk_sala;
    }

    public int getFk_sala_id() {
        return fk_sala_id;
    }

    public void setFk_sala_id(int fk_sala_id) {
        this.fk_sala_id = fk_sala_id;
    }

    public int getFk_ubi() {
        return fk_ubi;
    }

    public void setFk_ubi(int fk_ubi) {
        this.fk_ubi = fk_ubi;
    }

    public String getFk_ubi_name() {
        return fk_ubi_name;
    }

    public void setFk_ubi_name(String fk_ubi_name) {
        this.fk_ubi_name = fk_ubi_name;
    }

    public String getFk_sala_virt() {
        return fk_sala_virt;
    }

    public void setFk_sala_virt(String fk_sala_virt) {
        this.fk_sala_virt = fk_sala_virt;
    }

    public int getFk_sala_virt_id() {
        return fk_sala_virt_id;
    }

    public void setFk_sala_virt_id(int fk_sala_virt_id) {
        this.fk_sala_virt_id = fk_sala_virt_id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getFk_ponente() {
        return fk_ponente;
    }

    public void setFk_ponente(String fk_ponente) {
        this.fk_ponente = fk_ponente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre_es() {
        return nombre_es;
    }

    public void setNombre_es(String nombre_es) {
        this.nombre_es = nombre_es;
    }

    public String getNombre_en() {
        return nombre_en;
    }

    public void setNombre_en(String nombre_en) {
        this.nombre_en = nombre_en;
    }

    public String getNombre_fr() {
        return nombre_fr;
    }

    public void setNombre_fr(String nombre_fr) {
        this.nombre_fr = nombre_fr;
    }

    public String getDescripcion_es() {
        return descripcion_es;
    }

    public void setDescripcion_es(String descripcion_es) {
        this.descripcion_es = descripcion_es;
    }

    public String getDescripcion_en() {
        return descripcion_en;
    }

    public void setDescripcion_en(String descripcion_en) {
        this.descripcion_en = descripcion_en;
    }

    public String getDescripcion_fr() {
        return descripcion_fr;
    }

    public void setDescripcion_fr(String descripcion_fr) {
        this.descripcion_fr = descripcion_fr;
    }

    public String getRequisitos_es() {
        return requisitos_es;
    }

    public void setRequisitos_es(String requisitos_es) {
        this.requisitos_es = requisitos_es;
    }

    public String getRequisitos_en() {
        return requisitos_en;
    }

    public void setRequisitos_en(String requisitos_en) {
        this.requisitos_en = requisitos_en;
    }

    public String getRequisitos_fr() {
        return requisitos_fr;
    }

    public void setRequisitos_fr(String requisitos_fr) {
        this.requisitos_fr = requisitos_fr;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
