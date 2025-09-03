package Service;


import Model.Model;
import Repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository){
        this.produtoRepository = produtoRepository;
    }

    public List<Model> listarProdutos() {
        return produtoRepository.findAll();
    }

    public Optional<Model> buscarPorId(Long id){
        return produtoRepository.findById(id);
    }

    public Model salvarProduto( Model produto){
       return produtoRepository.save(produto);
    }


    public void deletarProduto(Long id){
        produtoRepository.deleteById(id);
    }
}
