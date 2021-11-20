package br.com.unijorge.tablemodel;

import br.com.unijorge.domain.Fabricante;
import br.com.unijorge.domain.Produto;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * Criação de um Modelo para tabelas que serão alimentadas por Fabricantes
 * @author Douglas
 */
public class FabricanteTableModel extends AbstractTableModel{

    private String colunas[] = {"Descrição"}; //Cabeçalho da tabela
    private List<Fabricante> fabricantes; //Lista de fabricantes que estarão na tabela
    
    /**
     * Constantes que serão utilizadas no getValueAt
     */
    private final int COLUNA_DESCRICAO = 0;

    public FabricanteTableModel(List<Fabricante> fabricantes) {
        this.fabricantes = fabricantes;
    }
    
    
    @Override
    public int getRowCount() { //Retorna a quantidade de linhas da tabela
        return fabricantes.size();
    }

    @Override
    public int getColumnCount() { //Retorna a quantidade de colunas da tabela
        return colunas.length;
    }
    
    @Override
    public String getColumnName(int indice) {//Retorna o nome da coluna
        return colunas[indice];
    }
    
    //Retorna o valor de acordo com a linha e coluna informadas.
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Fabricante f = this.fabricantes.get(rowIndex); //Seleciona o produto de acordo com a linha informada
        
        switch (columnIndex){ //Seleciona e retorna o atributo de acordo com a coluna informada
            
            case COLUNA_DESCRICAO ->{
                return f.getDescricao();
            }
        }
        return null;
    }
    
}
