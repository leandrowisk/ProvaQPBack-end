package com.qpLeandro.QPProva.controller;
import com.qpLeandro.QPProva.model.PessoaModel;
import com.qpLeandro.QPProva.service.PessoaService;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/v1/pessoa")
public class PessoaController {

    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/registrarName")
    public ResponseEntity<PessoaModel> save(@RequestBody PessoaModel pessoa) {
        PessoaModel novaPessoa = pessoaService.save(pessoa);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaPessoa);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/list")
    public ResponseEntity<List<PessoaModel>> findAll() {
        List<PessoaModel> pessoaList = pessoaService.findAll();
        return ResponseEntity.ok(pessoaList);
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<PessoaModel> getById(@PathVariable @Min(value = 1, message = "Erro: Id inválido") Long id) {
        PessoaModel pessoa = pessoaService.getById(id);
        return ResponseEntity.ok(pessoa);
    }

    @DeleteMapping("/list/{id}")
    public ResponseEntity<Void> delete(@PathVariable @Min(value = 1, message = "Erro: Id inválido") Long id) {
        pessoaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/findNacionalityByPerson/{nomePessoa}")
    public ResponseEntity<String> buscarNacionalidadePorNome(@PathVariable String nomePessoa) {
        String nacionalidade = pessoaService.consultarNacionalidade(nomePessoa);
        return ResponseEntity.ok(nacionalidade);
    }
}
