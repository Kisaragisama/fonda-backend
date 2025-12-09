package itch.fonda.repository;

import itch.fonda.entity.Venta;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VentaRepository extends JpaRepository<Venta, Long> {
    List<Venta> findByActivoTrue();
}