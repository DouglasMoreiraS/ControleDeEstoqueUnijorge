package br.com.unijorge.tablemodel;

import br.com.unijorge.domain.Produto;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import org.hibernate.hql.spi.id.AbstractTableBasedBulkIdHandler;

/**
 * Criação de um Modelo para tabelas que serão alimentadas por Produtos
 * @author Douglas
 */
public class ProdutoTableModel extends AbstractTableModel{

    private String colunas[] = {"Fabricante", "Descrição", "Quantidade"}; //Cabeçalho da tabela
    private List<Produto> produtos; //Lista de produtos que estarão na tabela
    
    /**
     * Constantes que serão utilizadas no getValueAt
     */
    private final int COLUNA_FABRICANTE = 0;
    private final int COLUNA_DESCRICAO = 1;
    private final int COLUNA_QUANTIDADE = 2;

    public ProdutoTableModel(List<Produto> produtos) {
        this.produtos = produtos;
    }
    
    
    @Override
    public int getRowCount() { //Retorna a quantidade de linhas da tabela
        return produtos.size();
    }

    @Override
    public int getColumnCount() { //Retorna a quantidade de colunas da tabela
        return colunas.length;
    }
    
    @Override
    public String getColumnName(int indice) { //Retorna o nome da coluna
        return colunas[indice];
    }
    
    //Retorna o valor de acordo com a linha e coluna informadas.
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Produto p = this.produtos.get(rowIndex); //Seleciona o produto de acordo com a linha informada
        
        switch (columnIndex){ //Seleciona e retorna o atributo de acordo com a coluna informada
            
            case COLUNA_FABRICANTE ->{
                return p.getFabricante().getDescricao();
            }
            
            case COLUNA_DESCRICAO ->{
                return p.getDescricao();
            }
            
            case COLUNA_QUANTIDADE ->{
                return p.getQuantidade();
            }
            
        }
        return null;
    }
    
}
