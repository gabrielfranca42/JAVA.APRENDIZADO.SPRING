package Service;


import Model.Model;
import Repository.ProdutoRepository;
import exceptions.RecursoNaoEncontradoException;
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

    public Model buscarPorId(Long id){
        return produtoRepository.findById(id)
                .orElseThrow(()-> new RecursoNaoEncontradoException("Produto com ID"+id+"nao encontrado"));
    }

    public Model salvarProduto( Model produto){
       return produtoRepository.save(produto);
    }


    public void deletarProduto(Long id){

        if(!produtoRepository.existsById(id)){
            throw new RecursoNaoEncontradoException("Produto com id"+id+"nao encontrado.");
        }

        produtoRepository.deleteById(id);
    }
}
