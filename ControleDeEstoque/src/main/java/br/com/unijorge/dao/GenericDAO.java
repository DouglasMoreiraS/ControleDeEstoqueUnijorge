package br.com.unijorge.dao;

import br.com.unijorge.util.HibernateUtil;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
/**
 * Essa classe é responsavel pelo CRUD das entidades, todas as entidades herdam dessa classe, assim não será necessário a criação de um CRUD para cada uma delas.
 * @param <Entidade> 
 */
public class GenericDAO<Entidade> {
    
    private Class<Entidade> classe;
    
    @SuppressWarnings("unchecked")
	public GenericDAO() {
        this.classe = (Class<Entidade>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
    
    public void salvar(Entidade entidade){
        
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        
        try{
            transacao = sessao.beginTransaction();            
            sessao.saveOrUpdate(entidade);            
            transacao.commit();            
        }catch (RuntimeException e){            
            if (transacao != null)
                transacao.rollback();           
            
            System.err.println("Erro ao salvar: " + e.getMessage());
            e.printStackTrace();
            throw e;            
        }finally{
            sessao.close();
        }
        
    }
    
    public List<Entidade> listar(){
        
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        
        try{
            Criteria consulta = sessao.createCriteria(classe);
            @SuppressWarnings("unchecked")
			List<Entidade> resultado = consulta.list();
            return resultado;
        } catch (RuntimeException e){
            throw e;
        } finally{
            sessao.close();
        }
    }
    
    @SuppressWarnings("unchecked")
	public List<Entidade> listar(String campoOrdenacao){
        
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        
        try{
            Criteria consulta = sessao.createCriteria(classe);
            consulta.addOrder(Order.asc(campoOrdenacao));
            List<Entidade> resultado = consulta.list();
            return resultado;
        } catch (RuntimeException e){
            throw e;
        } finally{
            sessao.close();
        }
    }
    
    @SuppressWarnings("unchecked")
	public Entidade buscar(Long codigo){
        
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        
        try{
            Criteria consulta = sessao.createCriteria(classe);
            consulta.add(Restrictions.idEq(codigo));
            Entidade resultado = (Entidade) consulta.uniqueResult();
            return resultado;
        } catch (RuntimeException e){
            throw e;
        } finally{
            sessao.close();
        }
    }
    
    public void excluir (Entidade entidade){
        
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        
        try{
            transacao = sessao.beginTransaction();            
            sessao.delete(entidade);            
            transacao.commit();            
        }catch (RuntimeException e){            
            if (transacao != null)
                transacao.rollback();           
            
            System.err.println("Erro ao salvar: " + e.getMessage());
            throw e;            
        }finally{
            sessao.close();
        }
        
    }
    
    public void editar(Entidade entidade){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        
        try{
            transacao = sessao.beginTransaction();            
            sessao.update(entidade);            
            transacao.commit();            
        }catch (RuntimeException e){            
            if (transacao != null)
                transacao.rollback();           
            
            System.err.println("Erro ao salvar: " + e.getMessage());
            throw e;            
        }finally{
            sessao.close();
        }
    }
    
    @SuppressWarnings("unchecked")
	public Entidade merge(Entidade entidade){
        
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        
        try{
            transacao = sessao.beginTransaction();            
            Entidade retorno = (Entidade) sessao.merge(entidade);            
            transacao.commit();    
            return retorno;
        }catch (RuntimeException e){            
            if (transacao != null)
                transacao.rollback();           
            
            System.err.println("Erro ao salvar: " + e.getMessage());
            throw e;            
        }finally{
            sessao.close();
        }
        
    }
    
}
