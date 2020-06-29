package com.helpenchentes.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.helpenchentes.models.Doacao;
import com.helpenchentes.models.Pessoa;
import com.helpenchentes.repository.DoacaoRepository;
import com.helpenchentes.repository.PessoaRepository;

@Controller
public class PessoaController {

	@Autowired
	private PessoaRepository er;
	
	@Autowired
	private DoacaoRepository cr;
	
	@RequestMapping(value="/cadastrarPessoa", method=RequestMethod.GET)
	public String form(){
		return "pessoa/formPessoa";
	}
	
	@RequestMapping(value="/cadastrarPessoa", method=RequestMethod.POST)
	public String form(@Valid Pessoa pessoa, BindingResult result, RedirectAttributes attributes){
		if(result.hasErrors()){
			attributes.addFlashAttribute("mensagem", "Verifique os campos!");
			return "redirect:/cadastrarPessoa";
		}
		
		er.save(pessoa);
		attributes.addFlashAttribute("mensagem", "Pessoa cadastrado com sucesso!");
		return "redirect:/cadastrarPessoa";
	}
	
	@RequestMapping("/pessoas")
	public ModelAndView listaPessoas(){
		ModelAndView mv = new ModelAndView("listaPessoas");
		Iterable<Pessoa> pessoas = er.findAll();
		mv.addObject("pessoas", pessoas);
		return mv;
	}
	
	@RequestMapping(value="/{codigo}", method=RequestMethod.GET)
	public ModelAndView detalhesPessoa(@PathVariable("codigo") long codigo){
		Pessoa pessoa = er.findByCodigo(codigo);
		ModelAndView mv = new ModelAndView("pessoa/detalhesPessoa");
		mv.addObject("pessoa", pessoa);
		
		Iterable<Doacao> doacoes = cr.findByPessoa(pessoa);
		mv.addObject("doacoes", doacoes);
		
		return mv;
	}
	
	@RequestMapping("/deletarPessoa")
	public String deletarPessoa(long codigo){
		Pessoa pessoa = er.findByCodigo(codigo);
		er.delete(pessoa);
		return "redirect:/pessoas";
	}
	
	
	@RequestMapping(value="/{codigo}", method=RequestMethod.POST)
	public String detalhesPessoaPost(@PathVariable("codigo") long codigo, @Valid Doacao doacoes,  BindingResult result, RedirectAttributes attributes){
		if(result.hasErrors()){
			attributes.addFlashAttribute("mensagem", "Verifique os campos!");
			return "redirect:/{codigo}";
		}
		Pessoa pessoa = er.findByCodigo(codigo);
		doacoes.setPessoa(pessoa);
		cr.save(doacoes);
		attributes.addFlashAttribute("mensagem", "Doador adicionado com sucesso!");
		return "redirect:/{codigo}";
	}
	
	@RequestMapping("/deletarDoador")
	public String deletarDoador(String rg){
		Doacao doacoes = cr.findByRg(rg);
		cr.delete(doacoes);
		
		Pessoa pessoa = doacoes.getPessoa();
		long codigoLong = pessoa.getCodigo();
		String codigo = "" + codigoLong;
		return "redirect:/" + codigo;
	}
}	
