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
            new Moto(null, "Honda", "CG 160", "ABC1234", "9C2KC1670FR123456", "KC16E1234567", "356789012345678", null, now.minusDays(5), now),
            new Moto(null, "Yamaha", "Fazer 250", "XYZ5678", "9C6KE1210E0001234", "KE12E1234567", "356789012345679", null, now.minusDays(10), now),
            new Moto(null, "Suzuki", "Yes 125", "LMN2345", "9CD11111111111111", "CD11E1234567", "356789012345680", null, now.minusDays(7), now),
            new Moto(null, "Kawasaki", "Ninja 400", "QWE9876", "JKAEXKG1XKDA12345", "EXKGE1234567", "356789012345681", null, now.minusDays(3), now),
            new Moto(null, "BMW", "G 310 R", "RTY6543", "MD2JG5003JAA12345", "JG50E1234567", "356789012345682", null, now.minusDays(15), now),
            new Moto(null, "Dafra", "Apache 150", "UOP3456", "9C6KC1670FR765432", "KC16E7654321", "356789012345683", null, now.minusDays(8), now),
            new Moto(null, "Shineray", "XY 50", "JKL1122", "LXY12345678901234", "XY50E1234567", "356789012345684", null, now.minusDays(6), now),
            new Moto(null, "Harley-Davidson", "Iron 883", "GHJ8899", "1HD4LE218GC123456", "HDLEE1234567", "356789012345685", null, now.minusDays(9), now),
            new Moto(null, "Triumph", "Street Twin", "BNM7788", "SMTTST12345678901", "STTNE1234567", "356789012345686", null, now.minusDays(12), now),
            new Moto(null, "KTM", "Duke 390", "VFR3344", "MD2JPJ400NC123456", "JPJ40E1234567", "356789012345687", null, now.minusDays(4), now)
        );

        motoRepository.saveAll(motos);


        var patios = List.of(
            new Patio(null, "Pátio Central", "Centro", 100, 20, "Principal pátio da cidade.", now.minusDays(10), now),
            new Patio(null, "Pátio Norte", "Zona Norte", 80, 35, "Pátio da região norte com vigilância 24h.", now.minusDays(15), now),
            new Patio(null, "Pátio Sul", "Zona Sul", 60, 10, "Pátio com acesso por biometria.", now.minusDays(5), now),
            new Patio(null, "Pátio Oeste", "Zona Oeste", 120, 60, "Grande capacidade e vagas cobertas.", now.minusDays(20), now),
            new Patio(null, "Pátio Leste", "Zona Leste", 90, 25, "Infraestrutura moderna e iluminação noturna.", now.minusDays(30), now),
            new Patio(null, "Pátio da Estação", "Estação Central", 50, 5, "Próximo à estação de trem.", now.minusDays(3), now),
            new Patio(null, "Pátio Aeroporto", "Aeroporto Internacional", 70, 15, "Ideal para motos em trânsito.", now.minusDays(7), now),
            new Patio(null, "Pátio Industrial", "Distrito Industrial", 150, 100, "Pátio para veículos de carga.", now.minusDays(25), now),
            new Patio(null, "Pátio Shopping", "Shopping Center", 40, 8, "Pátio com acesso direto ao shopping.", now.minusDays(12), now),
            new Patio(null, "Pátio Universitário", "Campus Universitário", 60, 30, "Estacionamento exclusivo para alunos.", now.minusDays(18), now)
        );

        patioRepository.saveAll(patios);


        var rfIds = List.of(
            new RfId(null, "Leitor Fixo", "Entrada principal", StatusType.ATIVO, now.minusDays(10), now),
            new RfId(null, "Leitor Móvel", "Bloco A", StatusType.ATIVO, now.minusDays(12), now),
            new RfId(null, "Antena", "Ponto de Controle 1", StatusType.INATIVO, now.minusDays(20), now),
            new RfId(null, "Leitor Fixo", "Saída lateral", StatusType.ATIVO, now.minusDays(5), now),
            new RfId(null, "Leitor Móvel", "Bloco B", StatusType.ATIVO, now.minusDays(7), now),
            new RfId(null, "Antena", "Zona de Estacionamento", StatusType.ATIVO, now.minusDays(15), now),
            new RfId(null, "Leitor Fixo", "Corredor Central", StatusType.INATIVO, now.minusDays(9), now),
            new RfId(null, "Leitor Móvel", "Bloco C", StatusType.ATIVO, now.minusDays(11), now),
            new RfId(null, "Antena", "Portão de Serviço", StatusType.INATIVO, now.minusDays(8), now),
            new RfId(null, "Leitor Fixo", "Portão Principal", StatusType.ATIVO, now.minusDays(6), now)
        );

        rfIdRepository.saveAll(rfIds);


        var sensores = List.of(
            new Sensor(null, "SEN001", SensorType.LOCALIZACAO, "Entrada Principal", StatusType.ATIVO, now.minusDays(10), now),
            new Sensor(null, "SEN002", SensorType.LOCALIZACAO, "Bloco A - Corredor 1", StatusType.ATIVO, now.minusDays(9), now),
            new Sensor(null, "SEN003", SensorType.LOCALIZACAO, "Estacionamento Setor 1", StatusType.INATIVO, now.minusDays(8), now),
            new Sensor(null, "SEN004", SensorType.LOCALIZACAO, "Saída Lateral", StatusType.ATIVO, now.minusDays(7), now),
            new Sensor(null, "SEN005", SensorType.LOCALIZACAO, "Bloco B - Entrada", StatusType.ATIVO, now.minusDays(6), now),
            new Sensor(null, "SEN006", SensorType.LOCALIZACAO, "Zona de Carga", StatusType.ATIVO, now.minusDays(5), now),
            new Sensor(null, "SEN007", SensorType.LOCALIZACAO, "Corredor Central", StatusType.INATIVO, now.minusDays(4), now),
            new Sensor(null, "SEN008", SensorType.LOCALIZACAO, "Bloco C - Estacionamento", StatusType.ATIVO, now.minusDays(3), now),
            new Sensor(null, "SEN009", SensorType.LOCALIZACAO, "Portão de Serviço", StatusType.INATIVO, now.minusDays(2), now),
            new Sensor(null, "SEN010", SensorType.LOCALIZACAO, "Portão Principal", StatusType.ATIVO, now.minusDays(1), now)
        );

        sensorRepository.saveAll(sensores);


        var usuarios = List.of(
            new Usuario(null, "João Silva", "joao.silva@email.com", "senha123", "12345678909", StatusType.ATIVO, "ADMIN", AreaType.OPERACIONAL, now.minusDays(10), now),
            new Usuario(null, "Maria Oliveira", "maria.oliveira@email.com", "senha456", "98765432100", StatusType.ATIVO, "SUPORTE", AreaType.OPERACIONAL, now.minusDays(9), now),
            new Usuario(null, "Carlos Souza", "carlos.souza@email.com", "senha789", "45678912312", StatusType.ATIVO, "USUARIO", AreaType.OPERACIONAL, now.minusDays(8), now),
            new Usuario(null, "Ana Lima", "ana.lima@email.com", "senha321", "32165498700", StatusType.INATIVO, "USUARIO", AreaType.OPERACIONAL, now.minusDays(7), now),
            new Usuario(null, "Fernanda Costa", "fernanda.costa@email.com", "senha654", "14725836900", StatusType.ATIVO, "SUPORTE", AreaType.OPERACIONAL, now.minusDays(6), now),
            new Usuario(null, "Roberto Nunes", "roberto.nunes@email.com", "senha987", "25836914700", StatusType.INATIVO, "USUARIO", AreaType.OPERACIONAL, now.minusDays(5), now),
            new Usuario(null, "Juliana Martins", "juliana.martins@email.com", "senha159", "96385274100", StatusType.ATIVO, "ADMIN", AreaType.OPERACIONAL, now.minusDays(4), now),
            new Usuario(null, "Paulo Henrique", "paulo.henrique@email.com", "senha753", "74185296300", StatusType.ATIVO, "USUARIO", AreaType.OPERACIONAL, now.minusDays(3), now),
            new Usuario(null, "Luciana Ferreira", "luciana.ferreira@email.com", "senha852", "36925814700", StatusType.ATIVO, "SUPORTE", AreaType.OPERACIONAL, now.minusDays(2), now),
            new Usuario(null, "Ricardo Gomes", "ricardo.gomes@email.com", "senha456", "15935725800", StatusType.ATIVO, "USUARIO", AreaType.OPERACIONAL, now.minusDays(1), now)
        );

        usuarioRepository.saveAll(usuarios);
    }
}
