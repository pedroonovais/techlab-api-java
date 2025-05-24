package br.com.techlab.techlab_api_java.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import br.com.techlab.techlab_api_java.model.Usuario;
import br.com.techlab.techlab_api_java.model.UsuarioFilter;
import jakarta.persistence.criteria.Predicate;

public class UsuarioSpecfication {
    
    public static Specification<Usuario> withFilters(UsuarioFilter filter) { 
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter.nome() != null && !filter.nome().isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("nome")), "%" + filter.nome().toLowerCase() + "%"));
            }

            if (filter.email() != null && !filter.email().isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("email")), "%" + filter.email().toLowerCase() + "%"));
            }

            if (filter.cpf() != null && !filter.cpf().isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("cpf")), "%" + filter.cpf().toLowerCase() + "%"));
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
