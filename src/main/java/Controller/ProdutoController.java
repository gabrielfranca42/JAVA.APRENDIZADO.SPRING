package Controller;


import Model.Model;
import Service.ProdutoService;
import Service.Service;
import exceptions.RecursoNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService){
        this.produtoService = produtoService;
    }

    @GetMapping 
    public List<Model> listarProdutos(){
        return produtoService.listarProdutos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarProduto(@PathVariable Long id){
        try {
            Model produto = produtoService.buscarPorId(id);
            return ResponseEntity.ok(produto);
        }
        catch (RecursoNaoEncontradoException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping
    public Model criarProduto(@RequestBody Model model){
        return produtoService.salvarProduto(model);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id){
        produtoService.deletarProduto(id); //
        return ResponseEntity.noContent().build();
    }
}

