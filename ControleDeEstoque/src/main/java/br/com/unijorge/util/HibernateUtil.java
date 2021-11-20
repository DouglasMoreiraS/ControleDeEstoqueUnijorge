package br.com.unijorge.util;

import br.com.unijorge.domain.Fabricante;
import br.com.unijorge.domain.Produto;
import java.util.Properties;
import java.io.IOException;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

/**
 * Essa classe é responsável por gerenciar o acesso ao banco de dados através da sessionFactory.
 * @author Douglas
 */

public class HibernateUtil { 

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) { //Caso a session ainda não tenha sido iniciada, ele entra aqui.
            try {
                Configuration configuration = new Configuration(); 
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver"); //Driver de comunicação do MYSQL
                settings.put(Environment.URL, "jdbc:mysql://localhost/unijorge"); //URL de acesso ao banco.
                settings.put(Environment.USER, "root"); //Usuario
                settings.put(Environment.PASS, "admin"); //Senha
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect"); //Dialect de comunicação do Banco de dados
                settings.put(Environment.SHOW_SQL, "true"); //Responsável por exibir no terminal os SQLs durante a transição.

                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

                settings.put(Environment.HBM2DDL_AUTO, "update");  //Atualiza na tabela do banco toda modificação que seja feita nos domains.

                configuration.setProperties(settings);

                configuration.addAnnotatedClass(Fabricante.class); //Mapeando classe Fabricante
                configuration.addAnnotatedClass(Produto.class); //Mapeando classe produto

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (HibernateException e) {
                throw new HibernateException(e); //Caso ocorra algum erro durante a criação do da session, ele lança esse exception que é tratado nas classes que chamam esse método.
            }
        }
        return sessionFactory;
    }

}
