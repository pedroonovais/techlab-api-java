package br.com.techlab.techlab_api_java.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.techlab.techlab_api_java.model.Moto;

public interface MotoRepository extends JpaRepository<Moto, Long>, JpaSpecificationExecutor<Moto> {
    

}
