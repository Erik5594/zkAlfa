package br.com.erik.dao;

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

	

}
