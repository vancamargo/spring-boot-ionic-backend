package com.camargo.cursomc.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.camargo.cursomc.domain.Categoria;
import com.camargo.cursomc.dto.CategoriaDTO;
import com.camargo.cursomc.repositories.CategoriaRepository;
import com.camargo.cursomc.services.exceptions.DataIntegrityException;
import com.camargo.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired 
	private CategoriaRepository repo; 
	
	
	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
			return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto nao encontrado! id:" + id + ", Tipo" + Categoria.class.getName()));
		
		
		}
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Categoria update(Categoria obj) {
		Categoria newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
		repo.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Nãoé possivel excluir categorias que tem produtos");
		}
	}
	
	public List<Categoria> findAll(){
		return repo.findAll();
	}
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction ){
		@SuppressWarnings("deprecation")
		PageRequest pageRequest = new PageRequest(page,linesPerPage,Direction.valueOf( direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Categoria fromDTO(@Valid CategoriaDTO objDto) {
		return new Categoria(objDto.getId(), objDto.getNome());
	}
	
	private void updateData(Categoria newObj, Categoria obj ) {
		newObj.setNome(obj.getNome());
		
	}


}
