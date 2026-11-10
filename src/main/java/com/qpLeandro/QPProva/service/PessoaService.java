package com.qpLeandro.QPProva.service;
import com.qpLeandro.QPProva.DTO.NationalizeDTO;
import com.qpLeandro.QPProva.model.PessoaModel;
import com.qpLeandro.QPProva.repository.PessoaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import java.util.List;

import static com.qpLeandro.QPProva.Utils.PessoaUtil.obterNacionalidadeFormatada;


@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public PessoaModel save(PessoaModel pessoa) {
        return pessoaRepository.save(pessoa);
    }

    public List<PessoaModel> findAll() {
        return pessoaRepository.findAll();
    }

    public PessoaModel getById(Long id) {
        return pessoaRepository.getById(id);
    }

    public void deleteById(Long id) {
        pessoaRepository.deleteById(id);
    }

    public String consultarNacionalidade(String nome) {
        NationalizeDTO response = RestClient.builder()
                .baseUrl("https://api.nationalize.io")
                .build().get()
                .uri("?name={nome}", nome)
                .retrieve()
                .body(NationalizeDTO.class);
        if (response == null || response.country().isEmpty()) {
            return "Nenhuma nacionalidade encontrada para o nome: " + nome;
        }
        NationalizeDTO.Country country = null;
        for (NationalizeDTO.Country c : response.country()) {
            if (country == null) country = c;
            else if (c.probability() > country.probability()) country = c;
        };

        return obterNacionalidadeFormatada(country.country_id());
    }


}
