package com.camargo.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camargo.cursomc.domain.Pedido;
import com.camargo.cursomc.repositories.PedidoRepository;
import com.camargo.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired 
	private PedidoRepository repo; 
	
	
	public Pedido find(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
			return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto nao encontrado! id:" + id + ", Tipo" + Pedido.class.getName()));
		
		
		}
}
