package br.com.erik.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Dao {
	protected EntityManager em;
	
	protected EntityManager abrirConexao() {
		if(conexaoEstaFechada()){
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres");
			em = emf.createEntityManager();
			em.getTransaction().begin();
		}
		return em;
	}
	
	protected void fecharConexao() {
		if(em != null && em.isOpen()){
			em.close();
		}
	}
	
	private boolean conexaoEstaAberta(){
		return em != null && em.isOpen();
	}
	
	private boolean conexaoEstaFechada(){
		return !conexaoEstaAberta();
	}
}
