package br.com.erik.nf;

import java.util.List;

import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zkplus.databind.DataBinder;
import org.zkoss.zul.Decimalbox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Textbox;

import br.com.erik.dao.NotaFiscalDao;
import br.com.erik.entity.Fatura;
import br.com.erik.entity.NotaFiscal;
import br.com.erik.imposto.ICMS;
import br.com.erik.imposto.ISS;
import br.com.erik.imposto.Imposto;

public class CIndex extends GenericForwardComposer {
	private DataBinder binder;
	private Textbox txtbxNomeCliente;
	private Decimalbox dcmlbxValorFatura;
	private Radiogroup rdgrpTipoImposto;
	private Textbox txtbxEmailCliente;
	@Wire
	private Listbox listaNotaFiscais;

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		binder = new AnnotateDataBinder(comp);
		binder.loadAll();
	}

	public void onClick$btnGerarNotaFiscal() {
		Clients.showBusy("Gerando nota fiscal");
		Events.echoEvent("onGerarNotaFiscal", this.self, null);
	}
	
	public void onClick$btnListarNotaFiscal() {
		Clients.showBusy("Listando...");
		Events.echoEvent("onListarNotaFiscal", this.self, null);
	}

	public void onGerarNotaFiscal() {
		Fatura fatura = new Fatura(this.txtbxNomeCliente.getValue(), this.dcmlbxValorFatura.getValue().doubleValue());
		String emailDestino = this.txtbxEmailCliente.getValue();
		Imposto imposto;

		if (this.rdgrpTipoImposto.getSelectedItem().getValue().toString().compareTo("1") == 0) {
			imposto = new ISS();
		} else {
			imposto = new ICMS();
		}

		new GeradorNotaFiscal().geraNota(fatura, imposto, emailDestino);		
		Clients.clearBusy();
		listaNotaFiscais.getModel().addListDataListener(new NFListDataListener());
		Messagebox.show("Nota Fiscal gerada com sucesso");
	}
	
	public void onListarNotaFiscal() {
		System.out.println("Listando as NF");
		List<NotaFiscal> notasFiscais = new NotaFiscalDao().listar();
		System.out.println("Quantidade de linhas: " + notasFiscais.size() );
		for (NotaFiscal nf : notasFiscais)
		{
			System.out.print(nf.getId());
		}
		if (listaNotaFiscais== null)
			listaNotaFiscais = new Listbox();
		listaNotaFiscais.setModel( new ListModelList<NotaFiscal>(notasFiscais));
		Clients.clearBusy();
		
		Messagebox.show("Notas Fiscais listadas com sucesso!");
	}
}
