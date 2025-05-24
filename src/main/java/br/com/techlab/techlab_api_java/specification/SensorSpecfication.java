package br.com.techlab.techlab_api_java.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import br.com.techlab.techlab_api_java.model.Sensor;
import br.com.techlab.techlab_api_java.model.SensorFilter;
import jakarta.persistence.criteria.Predicate;

public class SensorSpecfication {
    public static Specification<Sensor> withFilters(SensorFilter filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter.localizacaoFisica() != null && !filter.localizacaoFisica().isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("localizacaoFisica")), "%" + filter.localizacaoFisica().toLowerCase() + "%"));
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
