package automobile.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import automobile.entity.Model;

public interface ModelDao extends JpaRepository<Model, Long>{

}


