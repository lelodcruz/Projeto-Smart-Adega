package VOs;

public class Bebidas {
	private Integer id2;
	private Integer id_fk;
	private Integer quantidade;
	private String marca;
	private String origem;
	private String nome;


	public Bebidas() {
	}

	public Bebidas(Integer id2) {
		this.id2 = id2;
	}

	public Bebidas(Integer id2, Integer id_fk,Integer quantidade, String marca, String origem,
			String nome) {
		this.id2 = id2;
		this.id_fk = id_fk;
		this.quantidade = quantidade;
		this.marca = marca;
		this.origem = origem;
		this.nome = nome;

	}

	public Integer getId2() {
		return id2;
	}

	public void setId2(Integer id2) {
		this.id2 = id2;
	}


	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getOrigem() {
		return origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getId_fk() {
		return id_fk;
	}

	public void setId_fk(Integer id_fk) {
		this.id_fk = id_fk;
	}
}
