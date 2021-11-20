package br.com.unijorge.domain;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;


/** 
 * A classe GenericDomain garante que todas as classes domain possuam a implementação Serializable
 * e a mesma configuração do Hibernate para a PK.
 */
@MappedSuperclass //Informando ao Hibernate que se trata de uma superclass de mapeamento.
public class GenericDomain implements Serializable{
    
    @Id //Declaração de chave primaria
    @GeneratedValue(strategy = GenerationType.AUTO) //Definição para o MySQL sobre a geração da chave primária.
     private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    @Override //Sobrescrita do método equals() para facilitar a comparação dos objetos durante o desenvolvimento do projeto.
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final GenericDomain other = (GenericDomain) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
}
