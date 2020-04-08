package br.com.southsystem.analisador.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.com.southsystem.analisador.model.Cliente;
import br.com.southsystem.analisador.model.Venda;
import br.com.southsystem.analisador.model.Vendedor;

public class ResumoDTO {

	private Set<Cliente> clientes;
	private Set<Vendedor> vendedores;
	private List<Venda> vendas;
	
	public ResumoDTO() {
		this.clientes = new HashSet<>();
		this.vendedores = new HashSet<>();
		this.vendas = new ArrayList<>();
	}
	
	public Set<Cliente> getClientes() {
		return Collections.unmodifiableSet(clientes);
	}
	
	public Set<Vendedor> getVendedores() {
		return Collections.unmodifiableSet(vendedores);
	}
	
	public List<Venda> getVendas() {
		return Collections.unmodifiableList(vendas);
	}
	
	public void adicionar(Cliente cliente) {
		this.clientes.add(cliente);
	}
	
	public void adicionar(Vendedor vendedor) {
		this.vendedores.add(vendedor);
	}
	
	public void adicionar(Venda venda) {
		this.vendas.add(venda);
	}

	@Override
	public String toString() {
		return "ResumoDTO [clientes=" + clientes + ", vendedores=" + vendedores + ", vendas=" + vendas + "]";
	}
	
}
