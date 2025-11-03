package br.com.fiap.fintech.model;

import java.math.BigDecimal;
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
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "T_FINT_HISTORICOMOEDA")
public class HistoricoMoeda {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "historico_moeda_seq")
    @SequenceGenerator(name = "historico_moeda_seq", sequenceName = "SEQ_FINT_HISTORICOMOEDA", allocationSize = 1)
    @Column(name = "pk_id_historico")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_moeda", nullable = false)
    private Moeda moeda;

    @NotNull
    @DecimalMin(value = "0.0")
    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal valor;

    @NotNull
    @Column(name = "data_registro", nullable = false)
    private LocalDateTime dataRegistro;

    public HistoricoMoeda() {
    }

    public HistoricoMoeda(Long id, Moeda moeda, BigDecimal valor, LocalDateTime dataRegistro) {
        this.id = id;
        this.moeda = moeda;
        this.valor = valor;
        this.dataRegistro = dataRegistro;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Moeda getMoeda() {
        return moeda;
    }

    public void setMoeda(Moeda moeda) {
        this.moeda = moeda;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDateTime getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(LocalDateTime dataRegistro) {
        this.dataRegistro = dataRegistro;
    }
}
