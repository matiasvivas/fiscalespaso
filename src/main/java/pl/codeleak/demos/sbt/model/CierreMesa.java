package pl.codeleak.demos.sbt.model;

import pl.codeleak.demos.sbt.enumeradores.Distritos;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "cierre_mesa")
public class CierreMesa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(columnDefinition = "TEXT")
    private String fotoBase64;
    @Enumerated(EnumType.STRING)
    private Distritos distrito;
    private Integer numeroCircuito;
    private Integer numeroSeccion;

    private Integer numeroMesa;
    private Integer cantidadVotosLibertadAvanza;
    private String username;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraCierre;
    public CierreMesa() {
    }

    public CierreMesa(Integer id, String fotoBase64, Distritos distrito, Integer numeroCircuito, Integer numeroSeccion, Integer numeroMesa, Integer cantidadVotosLibertadAvanza, String username, Date fechaHoraCierre) {
        this.id = id;
        this.fotoBase64 = fotoBase64;
        this.distrito = distrito;
        this.numeroCircuito = numeroCircuito;
        this.numeroSeccion = numeroSeccion;
        this.numeroMesa = numeroMesa;
        this.cantidadVotosLibertadAvanza = cantidadVotosLibertadAvanza;
        this.username = username;
        this.fechaHoraCierre = fechaHoraCierre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFotoBase64() {
        return fotoBase64;
    }

    public void setFotoBase64(String fotoBase64) {
        this.fotoBase64 = fotoBase64;
    }

    public Distritos getDistrito() {
        return distrito;
    }

    public void setDistrito(Distritos distrito) {
        this.distrito = distrito;
    }

    public Integer getNumeroCircuito() {
        return numeroCircuito;
    }

    public void setNumeroCircuito(Integer numeroCircuito) {
        this.numeroCircuito = numeroCircuito;
    }

    public Integer getNumeroSeccion() {
        return numeroSeccion;
    }

    public void setNumeroSeccion(Integer numeroSeccion) {
        this.numeroSeccion = numeroSeccion;
    }

    public Integer getNumeroMesa() {
        return numeroMesa;
    }

    public void setNumeroMesa(Integer numeroMesa) {
        this.numeroMesa = numeroMesa;
    }

    public Integer getCantidadVotosLibertadAvanza() {
        return cantidadVotosLibertadAvanza;
    }

    public void setCantidadVotosLibertadAvanza(Integer cantidadVotosLibertadAvanza) {
        this.cantidadVotosLibertadAvanza = cantidadVotosLibertadAvanza;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getFechaHoraCierre() {
        return fechaHoraCierre;
    }

    public void setFechaHoraCierre(Date fechaHoraCierre) {
        this.fechaHoraCierre = fechaHoraCierre;
    }
}
