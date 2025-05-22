package br.com.techlab.techlab_api_java.specification;

import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

import br.com.techlab.techlab_api_java.model.Patio;
import br.com.techlab.techlab_api_java.model.PatioFilter;
import jakarta.persistence.criteria.Predicate;

public class PatioSpecification {

    public static Specification<Patio> withFilters(PatioFilter filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter.nome() != null && !filter.nome().isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("nome")), "%" + filter.nome().toLowerCase() + "%"));
            }

            if (filter.localizacao() != null && !filter.localizacao().isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("localizacao")), "%" + filter.localizacao().toLowerCase() + "%"));
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