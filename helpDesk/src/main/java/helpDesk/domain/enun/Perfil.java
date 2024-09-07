package helpDesk.domain.enun;

public enum Perfil {
	Admin(0,"Role_Admin"),Cliente(1,"Role_Cliente"),Tecnico(2,"Role_Tecnico");
	
	private Integer codigo;
	private String descricao;
	private Perfil(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	public Integer getCodigo() {
		return codigo;
	}
	public String getDescricao() {
		return descricao;
	}
	
	public static Perfil toEnum(Integer codigo) {
		if(codigo == null) {
			return null;
		}
		
		for(Perfil x : Perfil.values()) {
			if(codigo.equals(x.getCodigo())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Perfil Invalido!!");
	}
	
}
