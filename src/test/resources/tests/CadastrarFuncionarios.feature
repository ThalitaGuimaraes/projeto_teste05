#language: pt

Funcionalidade: Cadastrar funcionários 
  como um usuário do sistema
  eu quero cadastrar funcionários
  para que eu possa gerenciar funcionários

Esquema do Cenário: Cadastro de funcionários com sucesso
  Dado Acessar a página de cadastro de funcionários
  E Informar o nome do funcionário
  E Informar o cpf do funcionário 
  E Informar a matricula do funcionário
  E Informar a data de admissão do funcionário
  E Selecionar a empresa <empresa>
  E Selecionar o setor <setor>
  Quando Solicitar a realização do cadastro
  Então Sistema realiza o cadastro com sucesso
  
  Exemplos:
  | empresa            | setor                         |
  | "Empresa Modelo A" | "Gestão de Recursos Humanos"  |
  | "Empresa Modelo B" | "Setor Comercial"             |
  | "Empresa Modelo C" | "Desenvolvimento de Sistemas" |
  | "Empresa Modelo C" | "Contabilidade"               |