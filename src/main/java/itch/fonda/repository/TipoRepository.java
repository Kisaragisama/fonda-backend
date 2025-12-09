package itch.fonda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import itch.fonda.entity.Tipo;

@Repository
public interface TipoRepository extends JpaRepository<Tipo, Integer> {

}