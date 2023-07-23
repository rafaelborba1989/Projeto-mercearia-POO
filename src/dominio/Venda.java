package dominio;

import java.util.ArrayList;
import java.util.Date;

public class Venda {
	private int numero;
	private Cliente c;
	private float total;
	private Date dataVenda;
	private ArrayList<ProdutoVenda> produtos;
	
	
	public Venda(int numero, Date dataVenda, Cliente c) {
		this.c = c; 
		this.numero = numero;
		this.dataVenda = dataVenda;
		produtos = new ArrayList<ProdutoVenda>();
		total = 0;
	}
	
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public float getTotal() {
		return total;
	}
	public void setTotal() {
		int i;
		for(i=0; i < produtos.size(); i++) {
			this.total = this.total + produtos.get(i).getSubtotal();
		}
	}
	public Date getDataVenda() {
		return dataVenda;
	}
	public void setDataVenda(Date dataVenda) {
		this.dataVenda = dataVenda;
	}
	
	 
}
