package com.helpenchentes.repository;

import org.springframework.data.repository.CrudRepository;

import com.helpenchentes.models.Doacao;
import com.helpenchentes.models.Pessoa;

public interface DoacaoRepository extends CrudRepository<Doacao, String>{
	Iterable<Doacao> findByPessoa(Pessoa pessoa);
	Doacao findByRg(String rg);
}
