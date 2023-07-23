package persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import dominio.Estoque;
import dominio.Fornecedor;


public class EstoqueDAO {
	private String BUS = "SELECT * FROM estoque WHERE codigo = ?";
	private String REL = "SELECT * FROM estoque";
	private String INS = "INSERT INTO estoque(codigo, descricao, preco, fk_cnpj, qtdestoque) VALUES (?,?,?,?,?) "; 
	private String DEL = "DELETE FROM estoque WHERE codigo = ?";
	private String ALT = "UPDATE estoque set codigo = ?, descricao = ?, preco = ?, fk_cnpj =?, qtdestoque = ? ";
	private Conexao c;

	public Estoque buscar(String codigo) {
		Estoque estoque = null;
		Fornecedor f = null;
		FornecedorDAO fDAO = new FornecedorDAO();
		 try {
			 c.conectar();
			 PreparedStatement instrucao = c.getConexao().prepareStatement(BUS);
			 instrucao.setString(1, codigo);
			 ResultSet rs = instrucao.executeQuery();
			 if(rs.next()) {
				 f = fDAO.buscar(rs.getString("fk_cnpj"));
				 estoque = new Estoque(rs.getInt("codigo"),rs.getString("descricao"),rs.getFloat("preco"),rs.getInt("qtdestoque"), f);
			 }
			 c.desconectar();
			 
		 }catch(Exception e) {
			 System.out.println("Erro na busca"+e.getMessage());
		 }
		 return estoque;
	}
	
	public void excluir(String codigo) {
		try {
			c.conectar();
			PreparedStatement instrucao = c.getConexao().prepareStatement(DEL);
			instrucao.setString(1, codigo);
			instrucao.execute();
			c.desconectar();
		}catch(Exception e) {
			System.out.println("Erro na exclusão"+e.getMessage());
		}
	}
	
	public void incluir(Estoque estoque) {
		try {
			c.conectar();
			PreparedStatement instrucao = c.getConexao().prepareStatement(INS);
			instrucao.setInt(1, estoque.getCodigo());
			instrucao.setString(2, estoque.getDescricao());
			instrucao.setFloat(3, estoque.getPreco());
			instrucao.setString(4, estoque.getFornecedor().getCnpj());
			instrucao.setInt(5, estoque.getQtdEstoque());
			instrucao.execute();
			c.desconectar();
		}catch(Exception e){
			System.out.println("Erro ao inserir produto no estoque" + e.getMessage());
		}
	}
	
	public void alterar(Estoque estoque) {
		try {
			c.conectar();
			PreparedStatement instrucao = c.getConexao().prepareStatement(ALT);
			instrucao.setInt(1, estoque.getCodigo());
			instrucao.setString(2, estoque.getDescricao());
			instrucao.setFloat(3, estoque.getPreco());
			instrucao.setString(4, estoque.getFornecedor().getCnpj());
			instrucao.setInt(5, estoque.getQtdEstoque());
			instrucao.execute();
			c.desconectar();
		}catch(Exception e){
			System.out.println("Erro na alteração"+e. getMessage());
		}
	}

	
	public ArrayList<Estoque> relatorio() {
		Estoque estoque;
		Fornecedor f;
		FornecedorDAO fDAO = new FornecedorDAO();
		ArrayList<Estoque> listaestoque = new ArrayList<Estoque>();
		try {
			c.conectar();
			Statement instrucao = c.getConexao().createStatement();
			ResultSet rs = instrucao.executeQuery(REL);
			while(rs.next()) {
				f = fDAO.buscar(rs.getString("fk_cnpj"));
				estoque = new Estoque(rs.getInt("codigo"), rs.getString("descricao"),rs.getFloat("preco"), rs.getInt("qtdestoque"), f);
				listaestoque.add(estoque);
			}
			c.desconectar();
		}catch(Exception e){
			System.out.println("Erro ao emitir relatorio!" + e.getMessage());
		}
		return listaestoque;
	}

}
