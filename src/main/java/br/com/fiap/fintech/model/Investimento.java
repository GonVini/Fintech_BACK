package br.com.fiap.fintech.model;

import java.math.BigDecimal;
import java.time.LocalDate;

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
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "T_FINT_INVESTIMENTO")
public class Investimento {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "investimento_seq")
    @SequenceGenerator(name = "investimento_seq", sequenceName = "SEQ_FINT_INVESTIMENTO", allocationSize = 1)
    @Column(name = "pk_id_investimento")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_usuario", nullable = false)
    private Usuario usuario;

    @NotBlank
    @Size(max = 120)
    @Column(nullable = false, length = 120)
    private String nome;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    @Column(name = "valor_aplicado", nullable = false, precision = 15, scale = 2)
    private BigDecimal valorAplicado;

    @NotNull
    @Column(name = "data_aplicacao", nullable = false)
    private LocalDate dataAplicacao;

    @DecimalMin(value = "0.0")
    @Column(name = "retorno_previsto", precision = 15, scale = 2)
    private BigDecimal retornoPrevisto;

    public Investimento() {
    }

    public Investimento(Long id, Usuario usuario, String nome, BigDecimal valorAplicado, LocalDate dataAplicacao,
            BigDecimal retornoPrevisto) {
        this.id = id;
        this.usuario = usuario;
        this.nome = nome;
        this.valorAplicado = valorAplicado;
        this.dataAplicacao = dataAplicacao;
        this.retornoPrevisto = retornoPrevisto;
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getValorAplicado() {
        return valorAplicado;
    }

    public void setValorAplicado(BigDecimal valorAplicado) {
        this.valorAplicado = valorAplicado;
    }

    public LocalDate getDataAplicacao() {
        return dataAplicacao;
    }

    public void setDataAplicacao(LocalDate dataAplicacao) {
        this.dataAplicacao = dataAplicacao;
    }

    public BigDecimal getRetornoPrevisto() {
        return retornoPrevisto;
    }

    public void setRetornoPrevisto(BigDecimal retornoPrevisto) {
        this.retornoPrevisto = retornoPrevisto;
    }
}
