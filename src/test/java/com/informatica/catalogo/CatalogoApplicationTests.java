package com.informatica.catalogo;

import com.informatica.catalogo.model.ProdutoModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CatalogoApplicationTests {

	@Test
	void contextLoads() {
		ProdutoModel produtoModel = new ProdutoModel();
		produtoModel.setFabricante("Intel");

		Assertions.assertEquals(produtoModel.getFabricante(), "AMD");
	}

}
