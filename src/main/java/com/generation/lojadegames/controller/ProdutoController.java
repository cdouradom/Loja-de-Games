package com.generation.lojadegames.controller; // Define o pacote controller onde a classe ProdutoController está localizada

import java.util.List;// Importa a classe List para definir listas de objetos

import org.springframework.beans.factory.annotation.Autowired; // Importa a anotação Autowired para injeção de dependência
import org.springframework.http.HttpStatus; // Importa a enumeração HttpStatus para definir códigos de status HTTP
import org.springframework.http.ResponseEntity; //  Importa a classe ResponseEntity para representar respostas HTTP
import org.springframework.web.bind.annotation.CrossOrigin; // Importa a anotação CrossOrigin para configurar CORS
import org.springframework.web.bind.annotation.DeleteMapping; // Importa a anotação DeleteMapping para mapear requisições DELETE
import org.springframework.web.bind.annotation.GetMapping; // Importa a anotação GetMapping para mapear requisições GET
import org.springframework.web.bind.annotation.PathVariable; // Importa a anotação PathVariable para extrair variáveis da URL 
import org.springframework.web.bind.annotation.PostMapping; // Importa a anotação PostMapping para mapear requisições POST  
import org.springframework.web.bind.annotation.PutMapping; // Importa a anotação PutMapping para mapear requisições PUT
import org.springframework.web.bind.annotation.RequestBody; // Importa a anotação RequestBody para extrair o corpo da requisição
import org.springframework.web.bind.annotation.RequestMapping; // Importa a anotação RequestMapping para mapear URLs
import org.springframework.web.bind.annotation.ResponseStatus;  // Importa a anotação ResponseStatus para definir o status de resposta HTTP
import org.springframework.web.bind.annotation.RestController; // Importa a anotação RestController para definir um controlador REST
import org.springframework.web.server.ResponseStatusException;  // Importa a classe ResponseStatusException para lançar exceções com status HTTP

import com.generation.lojadegames.model.Produto; // Importa a classe Produto do pacote model
import com.generation.lojadegames.repository.CategoriaRepository; // Importa a interface CategoriaRepository
import com.generation.lojadegames.repository.ProdutoRepository; // Importa a interface ProdutoRepository

import jakarta.validation.Valid; // Importa a anotação Valid para validação de dados


// Define a classe ProdutoController como um controlador REST
@RestController // Indica que a classe é um controlador REST
@RequestMapping("/produtos") // Define o endpoint da API
@CrossOrigin(origins = "*", allowedHeaders = "*") // Configura CORS para permitir requisições de qualquer origem
public class ProdutoController { // Utiliza o nome ProdutoController para a classe do controlador REST
    
    @Autowired // Injeção de dependência do Spring
	private ProdutoRepository produtoRepository; // Instancia o repositório de Produto

	@Autowired // Injeção de dependência do Spring
	private CategoriaRepository categoriaRepository; // Instancia o repositório de Categoria

    @GetMapping // Mapeia requisições GET para esse método
	public ResponseEntity<List<Produto>> getAll() { // Define o método getAll que retorna uma lista de Produto dentro de um ResponseEntity
	    return ResponseEntity.ok(produtoRepository.findAll()); // Retorna a lista de postagens com status 200 (OK)

        // equivalente ao SELECT * FROM db_lojadegames.tb_postagens;
	}

	@GetMapping("/{id}") // Mapeia requisições GET com um ID na URL para esse método
	public ResponseEntity<Produto> getById(@PathVariable Long id) { // Extrai o ID da URL e define o método getById que retorna
		return produtoRepository.findById(id)   // Busca a Produto pelo ID
				.map(resposta -> ResponseEntity.ok(resposta)) // uma Produto dentro de um ResponseEntity
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build()); // Retorna a Produto com status 200 (OK) ou 404 (Not Found) se não encontrada
		
        // equivalente ao SELECT * FROM db_lojadegames.tb_postagens WHERE id = ?;
	}

	@GetMapping("/titulo/{titulo}") // Mapeia requisições GET com um título na URL para esse método
	public ResponseEntity<List<Produto>> getByTitulo(@PathVariable String titulo) { // Extrai o título da URL e define o método getByTitulo que retorna uma lista de Produto dentro de um ResponseEntity
		return ResponseEntity.ok(produtoRepository.findAllByTituloContainingIgnoreCase(titulo)); // Retorna a lista de postagens que contém o título, ignorando maiúsculas e minúsculas, com status 200 (OK)
		
        // equivalente ao SELECT * FROM db_lojadegames.tb_postagens WHERE titulo LIKE "%titulo%";
	}

    @GetMapping("/maior/{preco}") // Mapeia requisições GET com um preço na URL para esse método
    public ResponseEntity<List<Produto>> getByPrecoMaior(@PathVariable Float preco) { // Extrai o preço da URL e define o método getByPrecoMaior que retorna uma lista de Produto dentro de um ResponseEntity
        return ResponseEntity.ok(produtoRepository.findByPrecoGreaterThanOrderByPrecoAsc(preco)); // Retorna a lista de produtos com preço maior que o valor fornecido, em ordem crescente, com status 200 (OK)

        // equivalente ao SELECT * FROM db_lojadegames.tb_produtos WHERE preco > ? ORDER BY preco ASC;
    }

    @GetMapping("/maiorouigual/{preco}") // Mapeia requisições GET com um preço na URL para esse método
    public ResponseEntity<List<Produto>> getByPrecoMaiorOuIgual(@PathVariable Float preco) { // Extrai o preço da URL e define o método getByPrecoMaiorOuIgual que retorna uma lista de Produto dentro de um ResponseEntity
        return ResponseEntity.ok(produtoRepository.findByPrecoGreaterThanEqualOrderByPrecoAsc(preco)); // Retorna a lista de produtos com preço maior ou igual ao valor fornecido, em ordem crescente, com status 200 (OK)

        // equivalente ao SELECT * FROM db_lojadegames.tb_produtos WHERE preco >= ? ORDER BY preco ASC;
    }

    @GetMapping("/menor/{preco}") // Mapeia requisições GET com um preço na URL para esse método
    public ResponseEntity<List<Produto>> getByPrecoMenor(@PathVariable Float preco) { // Extrai o preço da URL e define o método getByPrecoMenor que retorna uma lista de Produto dentro de um ResponseEntity
        return ResponseEntity.ok(produtoRepository.findByPrecoLessThanOrderByPrecoDesc(preco)); // Retorna a lista de produtos com preço menor que o valor fornecido, em ordem decrescente, com status 200 (OK)

        // equivalente ao SELECT * FROM db_lojadegames.tb_produtos WHERE preco < ? ORDER BY preco DESC;
    }

    @GetMapping("/menorouigual/{preco}") // Mapeia requisições GET com um preço na URL para esse método
    public ResponseEntity<List<Produto>> getByPrecoMenorOuIgual(@PathVariable Float preco) { // Extrai o preço da URL e define o método getByPrecoMenorOuIgual que retorna uma lista de Produto dentro de um ResponseEntity
        return ResponseEntity.ok(produtoRepository.findByPrecoLessThanEqualOrderByPrecoDesc(preco)); // Retorna a lista de produtos com preço menor ou igual ao valor fornecido, em ordem decrescente, com status 200 (OK) 

        // equivalente ao SELECT * FROM db_lojadegames.tb_produtos WHERE preco <= ? ORDER BY preco DESC;
    }

    @PostMapping // Mapeia requisições POST para esse método
	public ResponseEntity<Produto> post(@Valid @RequestBody Produto produto) { // Define o método post que recebe um Produto no corpo da requisição e retorna uma Produto dentro de um ResponseEntity
		if (categoriaRepository.existsById(produto.getCategoria().getId())) { // Verifica se o categoria associado à Produto existe
			produto.setId(null); // Garante que o ID seja nulo para criar uma nova Produto
			return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produto)); // Salva a Produto e retorna com status 201 (Created)
		}
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria inexistente", null); // Se o categoria não existir, lança uma exceção com status 400 (Bad Request)
		
        // equivalente ao INSERT INTO tb_postagens (data, descricao, foto, preco, quantidade, titulo, categoria_id) VALUES (current_timestamp(), 'descricao', 'foto', preco, quantidade, 'titulo', categoria_id);
	}

	@PutMapping // Mapeia requisições PUT para esse método
	public ResponseEntity<Produto> put(@Valid @RequestBody Produto produto) { // Define o método put que recebe uma Produto no corpo da requisição e retorna uma Produto dentro de um ResponseEntity
		if (produtoRepository.existsById(produto.getId())) { // Verifica se a Produto com o ID fornecido existe
			if (categoriaRepository.existsById(produto.getCategoria().getId())) { // Verifica se o categoria associado à Produto existe
				return ResponseEntity.status(HttpStatus.OK).body(produtoRepository.save(produto)); // Se ambos existirem, atualiza a Produto e retorna com status 200 (OK)
			}
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria inexistente", null); // Se o categoria não existir, lança uma exceção com status 400 (Bad Request)
		}
		return ResponseEntity.notFound().build(); // Se a Produto não existir, retorna status 404 (Not Found)	
		
        // equivalente ao UPDATE tb_postagens SET data = current_timestamp(), descricao = 'descricao', foto = 'foto', preco = preco, quantidade = quantidade, titulo = 'titulo', categoria_id = categoria_id WHERE id = ?;
	}

	@ResponseStatus(HttpStatus.NO_CONTENT) // Define o status de resposta como 204 (No Content)
	@DeleteMapping("/{id}") // Mapeia requisições DELETE com um ID na URL para esse método
	public void delete(@PathVariable Long id) { // Extrai o ID da URL e define o método delete que não retorna nada
		java.util.Optional<Produto> produto = produtoRepository.findById(id); // Busca a Produto pelo ID
		if (produto.isEmpty()) // Verifica se a Produto existe
			throw new ResponseStatusException(HttpStatus.NOT_FOUND); // Se a Produto não existir, lança uma exceção com status 404 (Not Found)
		produtoRepository.deleteById(id); // Se existir, deleta a Produto pelo ID
		
        // equivalente ao DELETE FROM tb_postagens WHERE id = ?;
	}
}