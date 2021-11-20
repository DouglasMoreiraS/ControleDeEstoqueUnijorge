package br.com.unijorge.controller;

import br.com.unijorge.dao.FabricanteDAO;
import br.com.unijorge.dao.ProdutoDAO;
import br.com.unijorge.domain.Fabricante;
import br.com.unijorge.domain.Produto;
import br.com.unijorge.tablemodel.ProdutoTableModel;
import br.com.unijorge.view.ProdutoView;
import java.awt.Frame;
import java.util.List;

/**
 *
 * @author Douglas
 */
public class ProdutoController {

    private List<Produto> produtosLista;
    private Produto produto;
    private List<Fabricante> fabLista;
    private Fabricante fabricante;

    private ProdutoView view;

    private ProdutoDAO pDao;
    private FabricanteDAO fDao;

    public ProdutoController(Frame parent) { //Recebe um parent como parametro e passa o mesmo para o jDialog ProdutoView que é um modal.
        this.view = new ProdutoView(parent, true, this); //Instanciando a view;
        pDao = new ProdutoDAO();
        fDao = new FabricanteDAO();

        carregarLista();
        carregarView();

        view.setVisible(true);
    }

    /**
     * Carregando os dados do banco
     */
    private void carregarLista() {
        this.produtosLista = pDao.listar();
        this.fabLista = fDao.listar();
    }

    /**
     * Alimentando a View com informações do banco
     */
    private void carregarView() {
        view.getCbFabricantes().removeAllItems();
        fabLista.forEach(f -> view.getCbFabricantes().addItem(f.getDescricao()));

        ProdutoTableModel produtoTM = new ProdutoTableModel(produtosLista);
        view.getTblProdutos().setModel(produtoTM);
    }

    /**
     * Caso ocorra algum erro ao tentar salvar o produto será lançado um
     * RunTimeException para a View informar ao usuário
     *
     * @throws RuntimeException
     */
    public void salvarEditar(String desc, int fabricanteSelecionado, String qnt) throws RuntimeException {
        try {
            produto.setDescricao(desc);
            produto.setFabricante(fabLista.get(fabricanteSelecionado));
            produto.setQuantidade(Integer.parseInt(qnt));

            pDao.salvar(produto);
            carregarLista();
            carregarView();
        } catch (RuntimeException e) {
            throw e;
        }
    }

    /**
     * Caso ocorra algum erro ao tentar salvar o produto será lançado um
     * RunTimeException para a View informar ao usuário
     *
     * @throws RuntimeException
     */
    public void excluir() throws RuntimeException {
        try {
            pDao.excluir(produto);
            carregarLista();
            carregarView();
        } catch (RuntimeException e) {
            throw e;
        }
    }

    /**
     * Alterando produto selecionado na tabela
     *
     * @param selectedRow
     */
    public void setProdutoSelecionado(int selectedRow) {
        produto = produtosLista.get(selectedRow);
    }

    /**
     * Um produto novo será criado, limpando o objeto produto
     *
     */
    public void novo() {
        produto = new Produto();
    }

    public Produto getProduto() {
        return produto;
    }

}
