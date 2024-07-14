package automobile.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import automobile.entity.Make;

public interface MakeDao extends JpaRepository<Make, Long> {

}
