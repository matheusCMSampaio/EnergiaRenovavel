package br.com.fiap.EnergiaRenovavel.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;


import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;

    @NotBlank(message = "O nome não pode estar vazio.")
    @Size(min = 2, max = 100, message = "O nome deve ter entre 2 e 100 caracteres.")
    @Column(name = "nome")
    private String nome;

    @NotBlank(message = "O email é obrigatório.")
    @Email(message = "Informe um email válido.")
    @Size(max = 150, message = "O email deve ter no máximo 150 caracteres.")
    @Column(name = "email", unique = true)
    private String email;

    @NotBlank(message = "A senha é obrigatória.")
    @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres.")
    @Column(name = "senha")
    private String senha;

    @Pattern(regexp = "\\d{11}", message = "O telefone deve conter exatamente 11 dígitos.")
    @Column(name = "telefone")
    private String telefone;

    @NotNull(message = "O campo notificações ativas não pode ser nulo.")
    @Column(name = "notificacoes_ativas")
    private Boolean notificacoesAtivas;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "usuario_role_tab",
            joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_role")
    )
    private Set<Role> roles = new HashSet<>();


    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Boolean getNotificacoesAtivas() {
        return notificacoesAtivas;
    }

    public void setNotificacoesAtivas(Boolean notificacoesAtivas) {
        this.notificacoesAtivas = notificacoesAtivas;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
