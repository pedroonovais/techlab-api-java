package br.com.techlab.techlab_api_java.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import br.com.techlab.techlab_api_java.model.RfId;
import br.com.techlab.techlab_api_java.model.RfIdFilter;
import jakarta.persistence.criteria.Predicate;

public class RfIdSpecification {
    
    public static Specification<RfId> withFilters(RfIdFilter filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            
            if (filter.rfId() != null && !filter.rfId().isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("rfId")), "%" + filter.rfId().toLowerCase() + "%"));
            }
            
            if (filter.dataCadastroInicial() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("dataCadastro"), filter.dataCadastroInicial()));
            }
            
            if (filter.dataCadastroFinal() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("dataCadastro"), filter.dataCadastroFinal()));
            }
            
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
