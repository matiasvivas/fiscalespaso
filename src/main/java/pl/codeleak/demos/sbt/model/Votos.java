package pl.codeleak.demos.sbt.model;

public class Votos {

    private Integer positivos;
    private Integer porcentajeJoven;
    private Integer porcentajeNacional;

    public Votos() {
    }

    public Votos(Integer positivos, Integer porcentajeJoven, Integer porcentajeNacional) {
        this.positivos = positivos;
        this.porcentajeJoven = porcentajeJoven;
        this.porcentajeNacional = porcentajeNacional;
    }

    public Integer getPositivos() {
        return positivos;
    }

    public void setPositivos(Integer positivos) {
        this.positivos = positivos;
    }

    public Integer getPorcentajeJoven() {
        return porcentajeJoven;
    }

    public void setPorcentajeJoven(Integer porcentajeJoven) {
        this.porcentajeJoven = porcentajeJoven;
    }

    public Integer getPorcentajeNacional() {
        return porcentajeNacional;
    }

    public void setPorcentajeNacional(Integer porcentajeNacional) {
        this.porcentajeNacional = porcentajeNacional;
    }
}
