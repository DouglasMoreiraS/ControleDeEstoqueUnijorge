package br.com.unijorge.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity //Informando ao Hibernate que se trata de uma tabela do banco.
public class Fabricante extends GenericDomain{
    
    @Column(nullable = false, length = 50)//Campo não-nulo, tamanho do campo = 50 cacacteres;
    private String descricao;

    public Fabricante(String descricao) {
        this.descricao = descricao;
    }

    public Fabricante() {
    }
    
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
