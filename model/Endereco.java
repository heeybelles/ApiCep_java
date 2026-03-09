package br.edu.fatecpg.apicep.model;

public class Endereco {
    private String cep;
    private String logradouro;
    private String bairro;
    private String localidade;
    private String uf;

    public Endereco(String bairro, String cep, String localidade, String logradouro, String uf) { // talvez vazio
        this.bairro = bairro;
        this.cep = cep;
        this.localidade = localidade;
        this.logradouro = logradouro;
        this.uf = uf;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    @Override
    public String toString() {
        return "Endereco{" +
                "bairro='" + bairro + '\'' +
                ", cep='" + cep + '\'' +
                ", logradouro='" + logradouro + '\'' +
                ", localidade='" + localidade + '\'' +
                '}';
    }
}