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
            // Predicado
            List<Predicate> predicates = new ArrayList<>();

            if (filter.description() != null) {
                predicates.add(cb.like(
                        cb.lower(root.get("description")), "%" + filter.description().toLowerCase() + "%"));
            }

            if (filter.startDate() != null && filter.endDate() != null) {
                predicates.add(
                        cb.between(root.get("date"), filter.startDate(), filter.endDate()));
            }

            if (filter.startDate() != null && filter.endDate() == null) {
                predicates.add(
                        cb.equal(root.get("date"), filter.startDate()));
            }

            var arrayPredicates = predicates.toArray(new Predicate[0]);
            return cb.and(arrayPredicates);
        };
    }
}
