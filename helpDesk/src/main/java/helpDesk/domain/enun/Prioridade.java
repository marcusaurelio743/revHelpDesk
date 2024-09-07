package helpDesk.domain.enun;

public enum Prioridade {
	Baixa(0,"Baixa"),Media(1,"Media"),Alta(2,"Alta");
	
	private Integer codigo;
	private String descricao;
	private Prioridade(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	public Integer getCodigo() {
		return codigo;
	}
	public String getDescricao() {
		return descricao;
	}
	
	public static Prioridade toEnum(Integer codigo) {
		if(codigo == null) {
			return null;
		}
		
		for(Prioridade x : Prioridade.values()) {
			if(codigo.equals(x.getCodigo())) {
				return x;
			}
		}
		throw new IllegalArgumentException("prioridade Invalida!!");
	}
	
}
