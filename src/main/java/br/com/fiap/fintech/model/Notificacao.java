package br.com.fiap.fintech.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "T_FINT_NOTIFICACAO")
public class Notificacao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "notificacao_seq")
    @SequenceGenerator(name = "notificacao_seq", sequenceName = "SEQ_FINT_NOTIFICACAO", allocationSize = 1)
    @Column(name = "pk_id_nota")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_usuario", nullable = false)
    private Usuario usuario;

    @NotBlank
    @Column(columnDefinition = "CLOB", nullable = false)
    private String descricao;

    @Column(name = "data_publicacao", nullable = false)
    private LocalDateTime dataPublicacao = LocalDateTime.now();

    public Notificacao() {
    }

    public Notificacao(Long id, Usuario usuario, String descricao) {
        this.id = id;
        this.usuario = usuario;
        this.descricao = descricao;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(LocalDateTime dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }
}
