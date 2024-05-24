package VOs;



public class Tipos {

	private Integer id;
    private String tipo;
    private Float temp_de_servico;
    private Float tolerancia;
    private Integer reserva;
    private Integer idade;
    private Integer naadega;
    
    public Tipos() {
	}
    
    public Tipos(Integer id) {
		this.id = id;
	}
    
    public Tipos (Integer id, String tipo, Float temp_de_servico, Float tolerancia, Integer reserva, Integer idade, Integer naadega) {
		this.id = id;
		this.tipo = tipo;
		this.temp_de_servico = temp_de_servico;
		this.tolerancia = tolerancia;
		this.reserva = reserva;
		this.idade = idade;
		this.naadega = naadega;
	}
    
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Float getTemp_de_servico() {
		return temp_de_servico;
	}
	public void setTemp_de_servico(Float temp_de_servico) {
		this.temp_de_servico = temp_de_servico;
	}
	public Float getTolerancia() {
		return tolerancia;
	}
	public void setTolerancia(Float tolerancia) {
		this.tolerancia = tolerancia;
	}
	public Integer getReserva() {
		return reserva;
	}
	public void setReserva(Integer reserva) {
		this.reserva = reserva;
	}
	public Integer getIdade() {
		return idade;
	}
	public void setIdade(Integer idade) {
		this.idade = idade;
	}
    
	public Integer getNaadega() {
		return naadega;
	}
	public void setNaadega(Integer naadega) {
		this.naadega = naadega;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
}

