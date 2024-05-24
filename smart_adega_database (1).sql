-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 02-Jun-2020 às 15:37
-- Versão do servidor: 10.4.11-MariaDB
-- versão do PHP: 7.4.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `smart_adega_database`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `informacoes_da_bebida`
--

CREATE TABLE `informacoes_da_bebida` (
  `id2` int(11) NOT NULL,
  `id_fk` int(11) DEFAULT NULL,
  `quantidade` int(11) DEFAULT NULL,
  `marca` varchar(150) DEFAULT NULL,
  `origem` varchar(150) DEFAULT NULL,
  `nome` varchar(150) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `informacoes_da_bebida`
--

INSERT INTO `informacoes_da_bebida` (`id2`, `id_fk`, `quantidade`, `marca`, `origem`, `nome`) VALUES
(406, 887, 2, 'Absolut', 'Escócia', 'absolut vodka'),
(407, 887, 3, 'chapa', 'Brasil', 'chapinha'),
(411, 888, 1, '12', '12', '1214'),
(414, 887, 4, '7', '6', '7'),
(415, 885, 7, 'teste vinho branco', 'Hortolândia', 'teste 5'),
(416, 889, 1, 'Especial', 'Brasil', 'Oficial'),
(417, 891, 2, 'teste2', 'teste2', 'teste2');

-- --------------------------------------------------------

--
-- Estrutura da tabela `ligadesliga`
--

CREATE TABLE `ligadesliga` (
  `onoff` tinyint(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `ligadesliga`
--

INSERT INTO `ligadesliga` (`onoff`) VALUES
(1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `login`
--

CREATE TABLE `login` (
  `id` int(11) NOT NULL,
  `email` varchar(30) NOT NULL,
  `senha` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `login`
--

INSERT INTO `login` (`id`, `email`, `senha`) VALUES
(1, 'ficticio', '1234'),
(2, 'teste', '123'),
(3, 'teste@teste.com', '1234');

-- --------------------------------------------------------

--
-- Estrutura da tabela `propriedades`
--

CREATE TABLE `propriedades` (
  `id` int(11) NOT NULL,
  `tipo` varchar(100) DEFAULT NULL,
  `temp_de_servico` float DEFAULT NULL,
  `tolerancia` float DEFAULT NULL,
  `reserva` int(11) DEFAULT NULL,
  `idade` int(11) DEFAULT NULL,
  `naadega` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `propriedades`
--

INSERT INTO `propriedades` (`id`, `tipo`, `temp_de_servico`, `tolerancia`, `reserva`, `idade`, `naadega`) VALUES
(885, 'Vinho Branco', 3, 3, 3, 3, 0),
(887, 'Vodka', 12.02, 2.02, 5, 5, 0),
(888, 'Vinho Tinto', 1, 2, 1, 1, 0),
(889, 'Teste oficial', 9, 5, 2020, 1, 0),
(891, 'teste 2', 8.55, 2.17, 5, 5, 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `tabela`
--

CREATE TABLE `tabela` (
  `id` int(11) NOT NULL,
  `dataHora` datetime DEFAULT NULL,
  `enderecoIP` varchar(50) DEFAULT NULL,
  `valorSensor` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Índices para tabelas despejadas
--

--
-- Índices para tabela `informacoes_da_bebida`
--
ALTER TABLE `informacoes_da_bebida`
  ADD PRIMARY KEY (`id2`),
  ADD KEY `id_fk` (`id_fk`);

--
-- Índices para tabela `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `propriedades`
--
ALTER TABLE `propriedades`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `tabela`
--
ALTER TABLE `tabela`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `informacoes_da_bebida`
--
ALTER TABLE `informacoes_da_bebida`
  MODIFY `id2` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=418;

--
-- AUTO_INCREMENT de tabela `login`
--
ALTER TABLE `login`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de tabela `propriedades`
--
ALTER TABLE `propriedades`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=892;

--
-- AUTO_INCREMENT de tabela `tabela`
--
ALTER TABLE `tabela`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=468;

--
-- Restrições para despejos de tabelas
--

--
-- Limitadores para a tabela `informacoes_da_bebida`
--
ALTER TABLE `informacoes_da_bebida`
  ADD CONSTRAINT `id_fk` FOREIGN KEY (`id_fk`) REFERENCES `propriedades` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
