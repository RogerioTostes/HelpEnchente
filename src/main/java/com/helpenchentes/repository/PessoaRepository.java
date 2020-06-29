package com.helpenchentes.repository;

import org.springframework.data.repository.CrudRepository;

import com.helpenchentes.models.Pessoa;

public interface PessoaRepository extends CrudRepository<Pessoa, String>{
	Pessoa findByCodigo(long codigo);
}
