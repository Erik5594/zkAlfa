package br.com.erik.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class NotaFiscal {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name="valor_imposto")
	private double valorImposto;
	@Column(name="valor_bruto")
	private double valorBruto;
	@Transient
	private double valorTotal;
	
	
	public NotaFiscal() {
		super();
	}

	public NotaFiscal(double valorImposto, double valorBruto) {
		this.valorImposto = valorImposto;
		this.valorBruto = valorBruto;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getValorImposto() {
		return valorImposto;
	}

	public void setValorImposto(double valorImposto) {
		this.valorImposto = valorImposto;
	}

	public double getValorBruto() {
		return valorBruto;
	}

	public void setValorBruto(double valorBruto) {
		this.valorBruto = valorBruto;
	}

	public double getValorTotal() {
		return getValorBruto()+ getValorImposto();
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}
	


}
