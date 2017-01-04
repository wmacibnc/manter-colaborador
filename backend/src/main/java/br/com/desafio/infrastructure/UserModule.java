package br.com.desafio.infrastructure;

import com.google.inject.AbstractModule;

import br.com.desafio.dao.ColaboradorDAO;
import br.com.desafio.dao.impl.ColaboradorDAOImpl;
import br.com.desafio.service.ColaboradorService;
import br.com.desafio.service.impl.ColaboradorServiceImpl;

public class UserModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(ColaboradorDAO.class).to(ColaboradorDAOImpl.class);
        bind(ColaboradorService.class).to(ColaboradorServiceImpl.class);

    }
}
