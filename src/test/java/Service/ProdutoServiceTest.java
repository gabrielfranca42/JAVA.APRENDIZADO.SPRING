package Service;

import Model.Model;
import Repository.ProdutoRepository;
import exceptions.RecursoNaoEncontradoException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProdutoServiceTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @InjectMocks
    private ProdutoService produtoService;

    @Test
    public void testListarProdutos() {
        Model produto1 = new Model("Produto A", 10.0);
        Model produto2 = new Model("Produto B", 20.0);
        List<Model> produtos = Arrays.asList(produto1, produto2);

        when(produtoRepository.findAll()).thenReturn(produtos);

        List<Model> resultado = produtoService.listarProdutos();

        assertEquals(2, resultado.size());
        assertEquals("Produto A", resultado.get(0).getNome());
        assertEquals(10.0, resultado.get(0).getPreco());
        assertEquals("Produto B", resultado.get(1).getNome());
        assertEquals(20.0, resultado.get(1).getPreco());

        verify(produtoRepository, times(1)).findAll();
    }

    @Test
    public void testBuscarPorId_Sucesso() {
        Long id = 1L;
        Model produto = new Model("Produto A", 10.0);

        when(produtoRepository.findById(id)).thenReturn(Optional.of(produto));

        Model resultado = produtoService.buscarPorId(id);

        assertNotNull(resultado);
        assertEquals("Produto A", resultado.getNome());
        assertEquals(10.0, resultado.getPreco());

        verify(produtoRepository, times(1)).findById(id);
    }

    @Test
    public void testBuscarPorId_ProdutoNaoEncontrado() {
        Long id = 1L;

        when(produtoRepository.findById(id)).thenReturn(Optional.empty());

        RecursoNaoEncontradoException exception = assertThrows(RecursoNaoEncontradoException.class, () -> {
            produtoService.buscarPorId(id);
        });

        assertTrue(exception.getMessage().contains("Produto com ID" + id + "nao encontrado"));
        verify(produtoRepository, times(1)).findById(id);
    }

    @Test
    public void testSalvarProduto() {
        Model produto = new Model("Produto A", 10.0);

        when(produtoRepository.save(produto)).thenReturn(produto);

        Model resultado = produtoService.salvarProduto(produto);

        assertNotNull(resultado);
        assertEquals("Produto A", resultado.getNome());
        assertEquals(10.0, resultado.getPreco());

        verify(produtoRepository, times(1)).save(produto);
    }

    @Test
    public void testDeletarProduto_Sucesso() {
        Long id = 1L;

        when(produtoRepository.existsById(id)).thenReturn(true);
        doNothing().when(produtoRepository).deleteById(id);

        assertDoesNotThrow(() -> produtoService.deletarProduto(id));

        verify(produtoRepository, times(1)).existsById(id);
        verify(produtoRepository, times(1)).deleteById(id);
    }

    @Test
    public void testDeletarProduto_ProdutoNaoEncontrado() {
        Long id = 1L;

        when(produtoRepository.existsById(id)).thenReturn(false);

        RecursoNaoEncontradoException exception = assertThrows(RecursoNaoEncontradoException.class, () -> {
            produtoService.deletarProduto(id);
        });

        assertTrue(exception.getMessage().contains("Produto com id" + id + "nao encontrado."));
        verify(produtoRepository, times(1)).existsById(id);
        verify(produtoRepository, never()).deleteById(anyLong());
    }
}
