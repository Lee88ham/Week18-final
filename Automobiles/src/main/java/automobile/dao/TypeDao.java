package automobile.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import automobile.entity.Type;

public interface TypeDao extends JpaRepository<Type, Long>{

}
