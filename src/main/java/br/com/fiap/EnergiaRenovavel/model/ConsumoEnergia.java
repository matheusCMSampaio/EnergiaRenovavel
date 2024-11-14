package br.com.fiap.EnergiaRenovavel.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "consumo_energia")
public class ConsumoEnergia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_consumo")
    private Long id;
    @Column(name = "data_hora")
    private LocalDateTime dataHora;
    @Column(name = "consumo_kwh")
    private String consumo;
    @Column(name = "custo_estimado")
    private String custoEstimado;
    @Column(name = "status_consumo")
    private String status;

    public ConsumoEnergia() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public String getConsumo() {
        return consumo;
    }

    public void setConsumo(String consumo) {
        this.consumo = consumo;
    }

    public String getCustoEstimado() {
        return custoEstimado;
    }

    public void setCustoEstimado(String custoEstimado) {
        this.custoEstimado = custoEstimado;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
