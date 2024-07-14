package automobile.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Make {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long makeId;
	private String makeName;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToMany(mappedBy = "make", cascade = CascadeType.ALL,
			orphanRemoval = true)
	private Set<Model> models = new HashSet<>();
}
