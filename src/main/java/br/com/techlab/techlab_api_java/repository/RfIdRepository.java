package br.com.techlab.techlab_api_java.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.techlab.techlab_api_java.model.RfId;

public interface RfIdRepository extends JpaRepository<RfId, Long> {
    

}
