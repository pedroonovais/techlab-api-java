package br.com.techlab.techlab_api_java.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import br.com.techlab.techlab_api_java.model.Moto;
import br.com.techlab.techlab_api_java.model.MotoFilter;
import jakarta.persistence.criteria.Predicate;

public class MotoSpecification {
    
    public static Specification<Moto> withFilters(MotoFilter filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter.marca() != null && !filter.marca().isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("marca")), "%" + filter.marca().toLowerCase() + "%"));
            }

            if (filter.placa() != null && !filter.placa().isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("placa")), "%" + filter.placa().toLowerCase() + "%"));
            }

            if (filter.modelo() != null && !filter.modelo().isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("modelo")), "%" + filter.modelo().toLowerCase() + "%"));
            }

            if (filter.chassi() != null && !filter.chassi().isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("chassi")), "%" + filter.chassi().toLowerCase() + "%"));
            }

            if (filter.motor() != null && !filter.motor().isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("motor")), "%" + filter.motor().toLowerCase() + "%"));
            }

            if (filter.imeiIot() != null && !filter.imeiIot().isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("imeiIot")), "%" + filter.imeiIot().toLowerCase() + "%"));
            }

            if (filter.dataCadastroInicial() != null) {
                predicates.add(cb.greaterThanOrEqualTo(
                    root.get("dataCadastro"), filter.dataCadastroInicial().atStartOfDay()));
            }

            if (filter.dataCadastroFinal() != null) {
                predicates.add(cb.lessThanOrEqualTo(
                    root.get("dataCadastro"), filter.dataCadastroFinal().atTime(23, 59, 59)));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
