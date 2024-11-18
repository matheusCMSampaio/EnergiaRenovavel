package br.com.fiap.EnergiaRenovavel.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Date;

@Entity
@Table(name = "alerta")
public class Alerta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_alerta")
    private Long id;

    @NotBlank(message = "O tipo de alerta é obrigatório.")
    @Size(max = 50, message = "O tipo de alerta deve ter no máximo 50 caracteres.")
    @Column(name = "tipo_alerta", nullable = false)
    private String tipoAlerta;

    @NotBlank(message = "A mensagem do alerta é obrigatória.")
    @Size(max = 255, message = "A mensagem deve ter no máximo 255 caracteres.")
    @Column(name = "mensagem", nullable = false)
    private String mensagem;

    public Alerta() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoAlerta() {
        return tipoAlerta;
    }

    public void setTipoAlerta(String tipoAlerta) {
        this.tipoAlerta = tipoAlerta;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
