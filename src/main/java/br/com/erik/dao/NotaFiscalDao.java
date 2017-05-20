package br.com.erik.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.erik.entity.NotaFiscal;

public class NotaFiscalDao extends Dao{
	
	public void armazenarNoBanco(NotaFiscal notaFiscal) {
		try{
			abrirConexao();
			em.persist(notaFiscal);
			em.getTransaction().commit();
		}finally{
			fecharConexao();
		}
	}

	public List<NotaFiscal> listar() {
		List<NotaFiscal> listaNotaFiscal = new ArrayList<NotaFiscal>();
		try{
			abrirConexao();
			listaNotaFiscal = em.createQuery("from NotaFiscal", NotaFiscal.class).getResultList();
			em.getTransaction().commit();
		}finally{
			fecharConexao();
		}
		return listaNotaFiscal;
	}

}
