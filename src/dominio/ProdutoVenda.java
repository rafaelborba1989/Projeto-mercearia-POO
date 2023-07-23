package dominio;

public class ProdutoVenda extends Estoque {
	private int quantidade;
	private float subtotal;

	public ProdutoVenda(int codigo, String descricao, float preco, int quantidade) {
		super(codigo, descricao, preco);
		this.quantidade = quantidade;
		this.subtotal = (quantidade * preco);
	}
	
	public int getQuantidade() {
		return quantidade;
	}

	public float getSubtotal() {
		return subtotal;
	}

}
