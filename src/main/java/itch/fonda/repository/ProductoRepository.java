package itch.fonda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import itch.fonda.entity.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
	List<Producto> findByActivoTrue(); // Solo productos activos
}