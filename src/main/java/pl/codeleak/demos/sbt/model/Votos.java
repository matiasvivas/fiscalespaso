package pl.codeleak.demos.sbt.model;

public class Votos {

    private Integer positivos;
    private double porcentajeJoven;
    private double porcentajeNacional;

    public Votos() {
    }

    public Votos(Integer positivos, double porcentajeJoven, double porcentajeNacional) {
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

    public double getPorcentajeJoven() {
        return porcentajeJoven;
    }

    public void setPorcentajeJoven(double porcentajeJoven) {
        this.porcentajeJoven = porcentajeJoven;
    }

    public double getPorcentajeNacional() {
        return porcentajeNacional;
    }

    public void setPorcentajeNacional(double porcentajeNacional) {
        this.porcentajeNacional = porcentajeNacional;
    }
}
