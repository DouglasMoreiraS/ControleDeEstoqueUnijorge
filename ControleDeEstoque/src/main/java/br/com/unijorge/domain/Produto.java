package br.com.unijorge.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity //Informando ao Hibernate que se trata de uma tabela do banco.
public class Produto extends GenericDomain{
    
    @Column(nullable = false, length = 80)
    private String descricao;
    
    @Column(nullable = false)
    private Integer quantidade;
    
    @ManyToOne
    @JoinColumn(nullable = false)
    private Fabricante fabricante;

    public Produto(String descricao, Fabricante fabricante) {
        this.descricao = descricao;
        this.fabricante = fabricante;
    }

    public Produto() {
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Fabricante getFabricante() {
        return fabricante;
    }

    public void setFabricante(Fabricante fabricante) {
        this.fabricante = fabricante;
    }
    
    
}
