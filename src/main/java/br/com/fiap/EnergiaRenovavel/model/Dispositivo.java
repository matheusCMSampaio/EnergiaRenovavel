package br.com.fiap.EnergiaRenovavel.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.Date;

@Entity
@Table(name = "dispositivo")
public class Dispositivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_dispositivo")
    private Long id;

    @NotBlank(message = "O nome do dispositivo é obrigatório.")
    @Size(max = 100, message = "O nome do dispositivo deve ter no máximo 100 caracteres.")
    @Column(name = "nome_dispo")
    private String nome;

    @NotBlank(message = "O tipo do dispositivo é obrigatório.")
    @Size(max = 50, message = "O tipo do dispositivo deve ter no máximo 50 caracteres.")
    @Column(name = "tipo")
    private String tipo;

    @NotBlank(message = "O status do dispositivo é obrigatório.")
    @Pattern(regexp = "^(ativo|inativo|manutencao)$",
            message = "O status deve ser 'ativo', 'inativo' ou 'manutencao'.")
    @Column(name = "status")
    private String status;

    @NotNull(message = "A data de instalação é obrigatória.")
    @PastOrPresent(message = "A data de instalação deve ser no passado ou no presente.")
    @Column(name = "data_instalacao")
    @Temporal(TemporalType.DATE)
    private Date dataInstalacao;

    public Dispositivo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDataInstalacao() {
        return dataInstalacao;
    }

    public void setDataInstalacao(Date dataInstalacao) {
        this.dataInstalacao = dataInstalacao;
    }
}
