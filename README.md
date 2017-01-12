# manter-colaborador
Projeto para manter um colaborador.

Inicio: 02/01/2017.

Instalação do projeto Frontend

﻿- Instalar o GIT (Baixar instalador do próprio site do git)
	* pode ser necessário configurar o proxy. Para isso va ao arquivo "C:\Users\seu.usuario\.gitconfig"(se nao existir, crie) e adicione seguinte configuração
	
	[http]
		proxy = http://USUARIODAREDE:SENHA@10.27.1.97:8123
		
	[url "https://"]
		insteadOf = git://


- Instalar o NodeJS (Baixar instalador no site do próprio NodeJS)
	* pode ser necessário configurar o proxy. Para isso va ao arquivo "C:\Users\seu.usuario\.npmrc"(se nao existir, crie) e adicione seguinte configuração
	  proxy = http://USUARIODAREDE:SENHA@10.27.1.97:8123


- Instalar o Ruby (Baixar instalador no site do próprio Ruby)
	* pode ser necessário configurar o proxy. Para isso va ao arquivo "C:\Users\seu.usuario\.gemrc"(se nao existir, crie) e adicione seguinte configuração
	  gem: --http-proxy=http://USUARIODAREDE:SENHA@10.27.1.97:8123


- Instalar o Sass (Comando)

	#> gem install sass
	
	Se der problema pode ser erro de SSL. É um problema conhecido do Sass. Ver url abaixo:
	http://guides.rubygems.org/ssl-certificate-update/

	
- Instalar o Compass (Comando)

	#> gem install compass

	
- Instalar o bower (Comando)

	#> npm install -g bower
	* pode ser necessário configurar o proxy. Para isso va ao arquivo "C:\Users\seu.usuario\.bowerrc" e adicione seguinte configuração
		{
		  "timeout": 30000,
		  "proxy":"http://USUARIODAREDE:SENHA@10.27.1.97:8123",
		  "https-proxy":"http://USUARIODAREDE:SENHA@10.27.1.97:8123"
		}
	

- Instalar o grunt (comando)

	#> npm install -g grunt-cli
	

- Na pasta do projeto angular - frontend

	#> npm install
	#> bower install
	
	* Isso vai baixar e instalar todas as dependências do projeto
	* Se ocorrer problema de conexao verifique, nos passos acima, como configurar o proxy


Executar o projeto:
grunt server