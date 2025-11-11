USE loja_roupas_db;
CREATE TABLE categoria (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    descricao VARCHAR(255),
    dataCadastro DATE,
    status VARCHAR(20)
);

CREATE TABLE fornecedor (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(150) NOT NULL,
    contato VARCHAR(100),
    email VARCHAR(100),
    telefone VARCHAR(20)
);

CREATE TABLE produto (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(150) NOT NULL,
    id_categoria INT, 
    preco DOUBLE,
    quantidadeEstoque INT,
    
    tipo_produto VARCHAR(20) NOT NULL, 
    tamanho VARCHAR(10),
    material_roupa VARCHAR(50), 
    tipo_acessorio VARCHAR(50),
    material_acessorio VARCHAR(50),

    FOREIGN KEY (id_categoria) REFERENCES categoria(id)
);

CREATE TABLE pedido (
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_fornecedor INT,
    dataPedido DATE,  
    status VARCHAR(50),
    valorTotal DOUBLE,

    FOREIGN KEY (id_fornecedor) REFERENCES fornecedor(id)
);

CREATE TABLE movimento_estoque (
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_produto INT, 
    tipoMovimento VARCHAR(20), 
    quantidade INT, 
    dataMovimento DATE, 


    FOREIGN KEY (id_produto) REFERENCES produto(id)
);
