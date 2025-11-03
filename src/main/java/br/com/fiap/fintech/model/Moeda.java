package br.com.fiap.fintech.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "T_FINT_MOEDA")
public class Moeda {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "moeda_seq")
    @SequenceGenerator(name = "moeda_seq", sequenceName = "SEQ_FINT_MOEDA", allocationSize = 1)
    @Column(name = "pk_id_moeda")
    private Long id;

    @NotBlank
    @Size(max = 15)
    @Column(nullable = false, length = 15)
    private String simbolo;

    @NotBlank
    @Size(max = 80)
    @Column(nullable = false, length = 80)
    private String nome;

    @DecimalMin(value = "0.0")
    @Column(name = "valor_atual", precision = 15, scale = 2)
    private BigDecimal valorAtual;

    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;

    @OneToMany(mappedBy = "moeda", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<HistoricoMoeda> historicos = new HashSet<>();

    public Moeda() {
    }

    public Moeda(Long id, String simbolo, String nome) {
        this.id = id;
        this.simbolo = simbolo;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getValorAtual() {
        return valorAtual;
    }

    public void setValorAtual(BigDecimal valorAtual) {
        this.valorAtual = valorAtual;
    }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public Set<HistoricoMoeda> getHistoricos() {
        return historicos;
    }

    public void setHistoricos(Set<HistoricoMoeda> historicos) {
        this.historicos = historicos;
    }
}
