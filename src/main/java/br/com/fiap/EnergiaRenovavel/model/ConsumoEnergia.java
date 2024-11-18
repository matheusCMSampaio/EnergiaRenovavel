package br.com.fiap.EnergiaRenovavel.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "consumo_energia")
public class ConsumoEnergia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_consumo")
    private Long id;

    @NotNull(message = "A data e hora do consumo é obrigatória.")
    @Column(name = "data_hora", nullable = false)
    private LocalDateTime dataHora;

    @NotNull(message = "O consumo em kWh é obrigatório.")
    @Positive(message = "O consumo deve ser um valor positivo.")
    @Column(name = "consumo_kwh", nullable = false)
    private String consumo;

    @NotNull(message = "O custo estimado é obrigatório.")
    @PositiveOrZero(message = "O custo estimado não pode ser negativo.")
    @Column(name = "custo_estimado", nullable = false)
    private String custoEstimado;

    @NotBlank(message = "O status do consumo é obrigatório.")
    @Pattern(regexp = "^(normal|alto|critico)$",
            message = "O status deve ser 'normal', 'alto' ou 'critico'.")
    @Column(name = "status_consumo", nullable = false)
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
