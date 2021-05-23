package com.ealves.poc.exception;

public class EntidadeNaoEncontradaException extends NegocioException {

	private static final long serialVersionUID = 1L;
	
	private static final String MSG_ENTIDADE_NAO_ENCONTRADA = "Não existe um cadastro com código %d";

	public EntidadeNaoEncontradaException(String mensagem) {
		super(mensagem);

	}
	
	public EntidadeNaoEncontradaException(Long entidadeId) {
		this(String.format(MSG_ENTIDADE_NAO_ENCONTRADA, entidadeId));

	}
}
