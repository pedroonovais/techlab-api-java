package br.com.techlab.techlab_api_java.config;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import br.com.techlab.techlab_api_java.model.AreaType;
import br.com.techlab.techlab_api_java.model.Moto;
import br.com.techlab.techlab_api_java.model.Patio;
import br.com.techlab.techlab_api_java.model.RfId;
import br.com.techlab.techlab_api_java.model.Sensor;
import br.com.techlab.techlab_api_java.model.SensorType;
import br.com.techlab.techlab_api_java.model.StatusType;
import br.com.techlab.techlab_api_java.model.Usuario;
import br.com.techlab.techlab_api_java.repository.MotoRepository;
import br.com.techlab.techlab_api_java.repository.PatioRepository;
import br.com.techlab.techlab_api_java.repository.RfIdRepository;
import br.com.techlab.techlab_api_java.repository.SensorRepository;
import br.com.techlab.techlab_api_java.repository.UsuarioRepository;
import jakarta.annotation.PostConstruct;

@Configuration
public class DatabaseSeeder {

    @Autowired
    private MotoRepository motoRepository;

    @Autowired
    private PatioRepository patioRepository;

    @Autowired
    private RfIdRepository rfIdRepository;

    @Autowired
    private SensorRepository sensorRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostConstruct
    public void init() {
        var now = LocalDateTime.now();

        var motos = List.of(
            Moto.builder().marca("Honda").modelo("CG 160").placa("ABC1234").chassi("9C2KC1670FR123456").motor("KC16E1234567").imeiIot("356789012345678").dataCadastro(now.minusDays(5)).dataAtualizacao(now).build(),
            Moto.builder().marca("Yamaha").modelo("Fazer 250").placa("XYZ5678").chassi("9C6KE1210E0001234").motor("KE12E1234567").imeiIot("356789012345679").dataCadastro(now.minusDays(10)).dataAtualizacao(now).build(),
            Moto.builder().marca("Suzuki").modelo("Yes 125").placa("LMN2345").chassi("9CD11111111111111").motor("CD11E1234567").imeiIot("356789012345680").dataCadastro(now.minusDays(7)).dataAtualizacao(now).build(),
            Moto.builder().marca("Kawasaki").modelo("Ninja 400").placa("QWE9876").chassi("JKAEXKG1XKDA12345").motor("EXKGE1234567").imeiIot("356789012345681").dataCadastro(now.minusDays(3)).dataAtualizacao(now).build(),
            Moto.builder().marca("BMW").modelo("G 310 R").placa("RTY6543").chassi("MD2JG5003JAA12345").motor("JG50E1234567").imeiIot("356789012345682").dataCadastro(now.minusDays(15)).dataAtualizacao(now).build(),
            Moto.builder().marca("Dafra").modelo("Apache 150").placa("UOP3456").chassi("9C6KC1670FR765432").motor("KC16E7654321").imeiIot("356789012345683").dataCadastro(now.minusDays(8)).dataAtualizacao(now).build(),
            Moto.builder().marca("Shineray").modelo("XY 50").placa("JKL1122").chassi("LXY12345678901234").motor("XY50E1234567").imeiIot("356789012345684").dataCadastro(now.minusDays(6)).dataAtualizacao(now).build(),
            Moto.builder().marca("Harley-Davidson").modelo("Iron 883").placa("GHJ8899").chassi("1HD4LE218GC123456").motor("HDLEE1234567").imeiIot("356789012345685").dataCadastro(now.minusDays(9)).dataAtualizacao(now).build(),
            Moto.builder().marca("Triumph").modelo("Street Twin").placa("BNM7788").chassi("SMTTST12345678901").motor("STTNE1234567").imeiIot("356789012345686").dataCadastro(now.minusDays(12)).dataAtualizacao(now).build(),
            Moto.builder().marca("KTM").modelo("Duke 390").placa("VFR3344").chassi("MD2JPJ400NC123456").motor("JPJ40E1234567").imeiIot("356789012345687").dataCadastro(now.minusDays(4)).dataAtualizacao(now).build()
        );
        motoRepository.saveAll(motos);

        var patios = List.of(
            Patio.builder().nome("Pátio Central").localizacao("Centro").capacidadeTotal(100).vagasDisponiveis(20).descricao("Principal pátio da cidade.").dataCadastro(now.minusDays(10)).dataAtualizacao(now).build(),
            Patio.builder().nome("Pátio Norte").localizacao("Zona Norte").capacidadeTotal(80).vagasDisponiveis(35).descricao("Pátio da região norte com vigilância 24h.").dataCadastro(now.minusDays(15)).dataAtualizacao(now).build(),
            Patio.builder().nome("Pátio Sul").localizacao("Zona Sul").capacidadeTotal(60).vagasDisponiveis(10).descricao("Pátio com acesso por biometria.").dataCadastro(now.minusDays(5)).dataAtualizacao(now).build(),
            Patio.builder().nome("Pátio Oeste").localizacao("Zona Oeste").capacidadeTotal(120).vagasDisponiveis(60).descricao("Grande capacidade e vagas cobertas.").dataCadastro(now.minusDays(20)).dataAtualizacao(now).build(),
            Patio.builder().nome("Pátio Leste").localizacao("Zona Leste").capacidadeTotal(90).vagasDisponiveis(25).descricao("Infraestrutura moderna e iluminação noturna.").dataCadastro(now.minusDays(30)).dataAtualizacao(now).build(),
            Patio.builder().nome("Pátio da Estação").localizacao("Estação Central").capacidadeTotal(50).vagasDisponiveis(5).descricao("Próximo à estação de trem.").dataCadastro(now.minusDays(3)).dataAtualizacao(now).build(),
            Patio.builder().nome("Pátio Aeroporto").localizacao("Aeroporto Internacional").capacidadeTotal(70).vagasDisponiveis(15).descricao("Ideal para motos em trânsito.").dataCadastro(now.minusDays(7)).dataAtualizacao(now).build(),
            Patio.builder().nome("Pátio Industrial").localizacao("Distrito Industrial").capacidadeTotal(150).vagasDisponiveis(100).descricao("Pátio para veículos de carga.").dataCadastro(now.minusDays(25)).dataAtualizacao(now).build(),
            Patio.builder().nome("Pátio Shopping").localizacao("Shopping Center").capacidadeTotal(40).vagasDisponiveis(8).descricao("Pátio com acesso direto ao shopping.").dataCadastro(now.minusDays(12)).dataAtualizacao(now).build(),
            Patio.builder().nome("Pátio Universitário").localizacao("Campus Universitário").capacidadeTotal(60).vagasDisponiveis(30).descricao("Estacionamento exclusivo para alunos.").dataCadastro(now.minusDays(18)).dataAtualizacao(now).build()
        );
        patioRepository.saveAll(patios);

        var rfIds = List.of(
            RfId.builder().tipo("Leitor Fixo").localizacaoFisica("Entrada principal").status(StatusType.ATIVO).dataCadastro(now.minusDays(10)).dataAtualizacao(now).build(),
            RfId.builder().tipo("Leitor Móvel").localizacaoFisica("Bloco A").status(StatusType.ATIVO).dataCadastro(now.minusDays(12)).dataAtualizacao(now).build(),
            RfId.builder().tipo("Antena").localizacaoFisica("Ponto de Controle 1").status(StatusType.INATIVO).dataCadastro(now.minusDays(20)).dataAtualizacao(now).build(),
            RfId.builder().tipo("Leitor Fixo").localizacaoFisica("Saída lateral").status(StatusType.ATIVO).dataCadastro(now.minusDays(5)).dataAtualizacao(now).build(),
            RfId.builder().tipo("Leitor Móvel").localizacaoFisica("Bloco B").status(StatusType.ATIVO).dataCadastro(now.minusDays(7)).dataAtualizacao(now).build(),
            RfId.builder().tipo("Antena").localizacaoFisica("Zona de Estacionamento").status(StatusType.ATIVO).dataCadastro(now.minusDays(15)).dataAtualizacao(now).build(),
            RfId.builder().tipo("Leitor Fixo").localizacaoFisica("Corredor Central").status(StatusType.INATIVO).dataCadastro(now.minusDays(9)).dataAtualizacao(now).build(),
            RfId.builder().tipo("Leitor Móvel").localizacaoFisica("Bloco C").status(StatusType.ATIVO).dataCadastro(now.minusDays(11)).dataAtualizacao(now).build(),
            RfId.builder().tipo("Antena").localizacaoFisica("Portão de Serviço").status(StatusType.INATIVO).dataCadastro(now.minusDays(8)).dataAtualizacao(now).build(),
            RfId.builder().tipo("Leitor Fixo").localizacaoFisica("Portão Principal").status(StatusType.ATIVO).dataCadastro(now.minusDays(6)).dataAtualizacao(now).build()
        );
        rfIdRepository.saveAll(rfIds);

        var sensores = List.of(
            Sensor.builder().codigoIdentificacao("SEN001").tipo(SensorType.LOCALIZACAO).localizacaoFisica("Entrada Principal").status(StatusType.ATIVO).dataCadastro(now.minusDays(10)).dataAtualizacao(now).build(),
            Sensor.builder().codigoIdentificacao("SEN002").tipo(SensorType.LOCALIZACAO).localizacaoFisica("Bloco A - Corredor 1").status(StatusType.ATIVO).dataCadastro(now.minusDays(9)).dataAtualizacao(now).build(),
            Sensor.builder().codigoIdentificacao("SEN003").tipo(SensorType.LOCALIZACAO).localizacaoFisica("Estacionamento Setor 1").status(StatusType.INATIVO).dataCadastro(now.minusDays(8)).dataAtualizacao(now).build(),
            Sensor.builder().codigoIdentificacao("SEN004").tipo(SensorType.LOCALIZACAO).localizacaoFisica("Saída Lateral").status(StatusType.ATIVO).dataCadastro(now.minusDays(7)).dataAtualizacao(now).build(),
            Sensor.builder().codigoIdentificacao("SEN005").tipo(SensorType.LOCALIZACAO).localizacaoFisica("Bloco B - Entrada").status(StatusType.ATIVO).dataCadastro(now.minusDays(6)).dataAtualizacao(now).build(),
            Sensor.builder().codigoIdentificacao("SEN006").tipo(SensorType.LOCALIZACAO).localizacaoFisica("Zona de Carga").status(StatusType.ATIVO).dataCadastro(now.minusDays(5)).dataAtualizacao(now).build(),
            Sensor.builder().codigoIdentificacao("SEN007").tipo(SensorType.LOCALIZACAO).localizacaoFisica("Corredor Central").status(StatusType.INATIVO).dataCadastro(now.minusDays(4)).dataAtualizacao(now).build(),
            Sensor.builder().codigoIdentificacao("SEN008").tipo(SensorType.LOCALIZACAO).localizacaoFisica("Bloco C - Estacionamento").status(StatusType.ATIVO).dataCadastro(now.minusDays(3)).dataAtualizacao(now).build(),
            Sensor.builder().codigoIdentificacao("SEN009").tipo(SensorType.LOCALIZACAO).localizacaoFisica("Portão de Serviço").status(StatusType.INATIVO).dataCadastro(now.minusDays(2)).dataAtualizacao(now).build(),
            Sensor.builder().codigoIdentificacao("SEN010").tipo(SensorType.LOCALIZACAO).localizacaoFisica("Portão Principal").status(StatusType.ATIVO).dataCadastro(now.minusDays(1)).dataAtualizacao(now).build()
        );
        sensorRepository.saveAll(sensores);

        var usuarios = List.of(
            Usuario.builder().id(null).nome("João Silva").email("joao.silva@email.com").senha("senha123").cpf("55018966050").status(StatusType.ATIVO).perfil("ADMIN").area(AreaType.OPERACIONAL).dataCadastro(now.minusDays(10)).dataAtualizacao(now).build(),
            Usuario.builder().id(null).nome("Maria Oliveira").email("maria.oliveira@email.com").senha("senha456").cpf("09576609011").status(StatusType.ATIVO).perfil("SUPORTE").area(AreaType.OPERACIONAL).dataCadastro(now.minusDays(9)).dataAtualizacao(now).build(),
            Usuario.builder().id(null).nome("Carlos Souza").email("carlos.souza@email.com").senha("senha789").cpf("27487889076").status(StatusType.ATIVO).perfil("USUARIO").area(AreaType.OPERACIONAL).dataCadastro(now.minusDays(8)).dataAtualizacao(now).build(),
            Usuario.builder().id(null).nome("Ana Lima").email("ana.lima@email.com").senha("senha321").cpf("67282083053").status(StatusType.INATIVO).perfil("USUARIO").area(AreaType.OPERACIONAL).dataCadastro(now.minusDays(7)).dataAtualizacao(now).build(),
            Usuario.builder().id(null).nome("Fernanda Costa").email("fernanda.costa@email.com").senha("senha654").cpf("94323188005").status(StatusType.ATIVO).perfil("SUPORTE").area(AreaType.OPERACIONAL).dataCadastro(now.minusDays(6)).dataAtualizacao(now).build(),
            Usuario.builder().id(null).nome("Roberto Nunes").email("roberto.nunes@email.com").senha("senha987").cpf("62749826047").status(StatusType.INATIVO).perfil("USUARIO").area(AreaType.OPERACIONAL).dataCadastro(now.minusDays(5)).dataAtualizacao(now).build(),
            Usuario.builder().id(null).nome("Juliana Martins").email("juliana.martins@email.com").senha("senha159").cpf("54964102076").status(StatusType.ATIVO).perfil("ADMIN").area(AreaType.OPERACIONAL).dataCadastro(now.minusDays(4)).dataAtualizacao(now).build(),
            Usuario.builder().id(null).nome("Paulo Henrique").email("paulo.henrique@email.com").senha("senha753").cpf("42783173018").status(StatusType.ATIVO).perfil("USUARIO").area(AreaType.OPERACIONAL).dataCadastro(now.minusDays(3)).dataAtualizacao(now).build(),
            Usuario.builder().id(null).nome("Luciana Ferreira").email("luciana.ferreira@email.com").senha("senha852").cpf("74918657044").status(StatusType.ATIVO).perfil("SUPORTE").area(AreaType.OPERACIONAL).dataCadastro(now.minusDays(2)).dataAtualizacao(now).build(),
            Usuario.builder().id(null).nome("Ricardo Gomes").email("ricardo.gomes@email.com").senha("senha456").cpf("25880036057").status(StatusType.ATIVO).perfil("USUARIO").area(AreaType.OPERACIONAL).dataCadastro(now.minusDays(1)).dataAtualizacao(now).build()
        );
        usuarioRepository.saveAll(usuarios);
    }
}
