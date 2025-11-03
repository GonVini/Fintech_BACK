package br.com.fiap.fintech.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "T_FINT_VALIDACAO")
public class Validacao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "validacao_seq")
    @SequenceGenerator(name = "validacao_seq", sequenceName = "SEQ_FINT_VALIDACAO", allocationSize = 1)
    @Column(name = "pk_id_validacao")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_usuario", nullable = false, unique = true)
    private Usuario usuario;

    @NotNull
    @Column(nullable = false)
    private Boolean status;

    public Validacao() {
    }

    public Validacao(Long id, Usuario usuario, Boolean status) {
        this.id = id;
        this.usuario = usuario;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
