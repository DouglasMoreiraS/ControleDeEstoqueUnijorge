package br.com.unijorge.controller;

import br.com.unijorge.dao.FabricanteDAO;
import br.com.unijorge.domain.Fabricante;
import br.com.unijorge.tablemodel.FabricanteTableModel;
import br.com.unijorge.view.FabricanteView;
import java.awt.Frame;
import java.util.List;

public class FabricanteController {

    private List<Fabricante> fLista;
    private Fabricante fabricante;

    private FabricanteView view;

    private FabricanteDAO fDao;

    public FabricanteController(Frame parent) {//Recebe um parent como parametro e passa o mesmo para o jDialog FabricanteView que é um modal.
        this.view = new FabricanteView(parent, true, this);
         fDao = new FabricanteDAO();
        carregarLista();
        carregarView();

        view.setVisible(true);
    }

    /**
     * Carregando os dados do banco
     */
    private void carregarLista() {
        this.fLista = fDao.listar();
    }

    /**
     * Alimentando a View com informações do banco
     */
    private void carregarView() {
        FabricanteTableModel fTableModel = new FabricanteTableModel(fLista);
        view.getTblFabricantes().setModel(fTableModel);
    }

    /**
     * Caso ocorra algum erro ao tentar salvar o fabricante será lançado um
     * RunTimeException para a View informar ao usuário
     *
     * @throws RuntimeException
     */
    public void salvarEditar(String desc) throws RuntimeException {
        try {
            fabricante.setDescricao(desc);
            fDao.salvar(fabricante);
            carregarLista();
            carregarView();
        } catch (RuntimeException e) {
            throw e;
        }
    }

    /**
     * Caso ocorra algum erro ao tentar salvar o fabricante será lançado um
     * RunTimeException para a View informar ao usuário
     *
     * @throws RuntimeException
     */
    public void excluir() throws RuntimeException {
        try{
            fDao.excluir(fabricante);
            carregarLista();
            carregarView();
        }catch (RuntimeException e){
            throw e;
        }
    }
 /**
     * Alterando fabricante selecionado na tabela
     *
     * @param selectedRow
     */
    public void setProdutoSelecionado(int selectedRow) {
        if (selectedRow < 0) {
            fabricante = fLista.get(selectedRow);
        }
    }
    
    /**
     * Um fabricante novo será criado, limpando o objeto fabricante
     */
    public void novo(){
        fabricante = new Fabricante();
    }

    public Fabricante getFabricante() {
        return fabricante;
    }

    public void setFabricante(Fabricante fabricante) {
        this.fabricante = fabricante;
    }
    
    
    
    
}
