/**
 * Controle De Estoque
 * 
 * Projeto desenvolvido pela equipe 20
 * 
 * Contribuintes:
 * -Douglas Moreira de São Tiago
 * -Guilherme Lima da Silva
 * -Paulo de Castro Nascimento
 * -Sérgio Henrique Souza Santos
 * -Manuela Maria Mangueira Santos
 *
 * Esse projeto foi desenvolvido em Java SE 17 junto com Maven, utilizando como persistencia o MySQL 8.0.26
 *
 * Possui as seguintes dependencias:
 * -Hibernate 5.5.4;
 * -FlatLaf 1.4
 * -MySQL Connector 8.0.26
 *
 */
package br.com.unijorge.main;

import br.com.unijorge.util.HibernateUtil;
import br.com.unijorge.view.View;
import com.formdev.flatlaf.FlatDarkLaf;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.hibernate.HibernateException;

public class Main {

    public static void main(String[] args) {

        /**
         * Antes de iniciar, ele verifica a conexão com o banco de dados, logo
         * após, define o LookAndFeel FlatDark
         */
        try {
            HibernateUtil.getSessionFactory();
            UIManager.setLookAndFeel(new FlatDarkLaf());
            
        } catch (HibernateException e) {
            /**
             * Caso exista algum problema com a comunicação com o banco de dados
             * é exibida uma mensagem informando que o sistema será encerrado.
             */
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao tentar se conectar ao banco de dados, o sistema será encerrado", "DBA Acess", JOptionPane.WARNING_MESSAGE);
            System.exit(0);
        } catch (UnsupportedLookAndFeelException ex) {
            /**
             * Não é necessário informar ao usuário que ocorreu falha ao definir o LookAndFeel
             */
        }
        
        View view = new View();
        view.setVisible(true);
        
    }

}
