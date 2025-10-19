package com.generation.lojadegames.controller;

import java.util.List; // Importa a classe List para trabalhar com listas
import java.util.Optional; // Importa a classe Optional para lidar com valores que podem estar ausentes
import org.springframework.beans.factory.annotation.Autowired; // Importa a anotação Autowired para injeção de dependências
import org.springframework.http.HttpStatus; // Importa a enumeração HttpStatus para definir códigos de status HTTP
import org.springframework.http.ResponseEntity; // Importa a classe ResponseEntity para manipular respostas HTTP
import org.springframework.web.bind.annotation.CrossOrigin; // Importa a anotação CrossOrigin para configurar CORS
import org.springframework.web.bind.annotation.DeleteMapping; // Importa a anotação DeleteMapping para mapear requisições DELETE
import org.springframework.web.bind.annotation.GetMapping; // Importa a anotação GetMapping para mapear requisições GET
import org.springframework.web.bind.annotation.PathVariable; //	 Importa a anotação PathVariable para extrair variáveis da URL
import org.springframework.web.bind.annotation.PostMapping; // Importa a anotação PostMapping para mapear requisições POST
import org.springframework.web.bind.annotation.PutMapping; // Importa a anotação PutMapping para mapear requisições PUT
import org.springframework.web.bind.annotation.RequestBody; // Importa a anotação RequestBody para extrair o corpo da requisição
import org.springframework.web.bind.annotation.RequestMapping; // Importa a anotação RequestMapping para mapear URLs
import org.springframework.web.bind.annotation.ResponseStatus; // Importa a anotação ResponseStatus para definir o status de resposta padrão
import org.springframework.web.bind.annotation.RestController; // Importa a anotação RestController para definir um controlador REST
import org.springframework.web.server.ResponseStatusException; // Importa a classe ResponseStatusException para lançar exceções com status HTTP
import com.generation.lojadegames.model.Categoria; // Importa a classe Categoria da model
import com.generation.lojadegames.repository.CategoriaRepository; // Importa a interface CategoriaRepository da repository
import jakarta.validation.Valid; // Importa a anotação Valid para validação de dados

@RestController
@RequestMapping("/categorias")
@CrossOrigin(origins = "*", allowedHeaders = "*")   
public class CategoriaController {   

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public ResponseEntity<List<Categoria>> getAll() {
        return ResponseEntity.ok(categoriaRepository.findAll());       
        // equivalente ao SELECT * FROM db_lojadegames.tb_categorias;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> getById(@PathVariable Long id) {
        return categoriaRepository.findById(id)
                .map(resposta -> ResponseEntity.ok(resposta))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
        // equivalente ao SELECT * FROM db_lojadegames.tb_categorias WHERE id = ?;
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Categoria>> getByNome(@PathVariable String nome) {
        return ResponseEntity.ok(categoriaRepository.findAllByNomeContainingIgnoreCase(nome));
        // equivalente ao SELECT * FROM db_lojadegames.tb_categorias WHERE nome LIKE "%nome%";
    }
   
    @PostMapping // Mapeia requisições POST para esse método
	public ResponseEntity<Categoria> postCategoria(@Valid @RequestBody Categoria categoria) { // Extrai o corpo da requisição e define o método postCategoria que recebe uma Categoria válida e retorna uma Categoria dentro de um ResponseEntity
        categoria.setId(null);
        return ResponseEntity.status(HttpStatus.CREATED) // Retorna a Categoria criada com status 201 (Created)
            .body(categoriaRepository.save(categoria)); // Salva a nova Categoria no banco de dados        
        // equivalente ao INSERT INTO tb_categorias (nome) VALUES ('nome');
    }

    @PutMapping // Mapeia requisições PUT para esse método
	public ResponseEntity<Categoria> put(@Valid @RequestBody Categoria categoria) { //	 Define o método put que recebe uma Categoria no corpo da requisição e retorna uma Categoria dentro de um ResponseEntity
		return categoriaRepository.findById(categoria.getId()) // Verifica se a categoria com o ID fornecido existe
				.map(resposta -> ResponseEntity.status(HttpStatus.OK).body(categoriaRepository.save(categoria))) // Se existir, atualiza a categoria e retorna com status 200 (OK)
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build()); // Se não existir, retorna status 404 (Not Found)
		// equivalente ao UPDATE tb_categorias SET nome = 'nome' WHERE id = ?;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT) // Define o status de resposta como 204 (No Content)
    @DeleteMapping("/{id}") // Mapeia requisições DELETE com um ID na URL para esse método
    public void delete(@PathVariable Long id) { // Extrai o ID da URL e define o método delete que não retorna nada
        Optional<Categoria> categoria = categoriaRepository.findById(id); // Ver
        if (categoria.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND); // Se a categoria não existir, lança uma exceção com status 404 (Not Found)
            categoriaRepository.deleteById(id); // Se existir, deleta o categoria pelo ID
        // equivalente ao DELETE FROM tb_categorias WHERE id = ?;
    }

}
