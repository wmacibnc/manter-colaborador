package br.com.desafio.modelo;

import java.io.Serializable;

public abstract class EntidadeBase implements Serializable {

	private static final long serialVersionUID = 384595976282254108L;

	public abstract Long getId();
}
