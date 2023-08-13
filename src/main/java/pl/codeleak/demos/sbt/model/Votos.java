package pl.codeleak.demos.sbt.model;

public class Votos {

    private Integer positivos;
    private String porcentajeJoven;
    private String porcentajeNacional;

    public Votos() {
    }

    public Votos(Integer positivos, String porcentajeJoven, String porcentajeNacional) {
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

    public String getPorcentajeJoven() {
        return porcentajeJoven;
    }

    public void setPorcentajeJoven(String porcentajeJoven) {
        this.porcentajeJoven = porcentajeJoven;
    }

    public String getPorcentajeNacional() {
        return porcentajeNacional;
    }

    public void setPorcentajeNacional(String porcentajeNacional) {
        this.porcentajeNacional = porcentajeNacional;
    }
}
