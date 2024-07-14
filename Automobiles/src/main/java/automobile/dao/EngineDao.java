package automobile.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import automobile.entity.Engine;

public interface EngineDao extends JpaRepository<Engine, Long> {

}
