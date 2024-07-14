package automobile.controller.model;

import java.util.HashSet;
import java.util.Set;

import automobile.entity.Type;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AutomobileType {

	private Long typeId;
	private String typeName;
	
	private Set<AutomobileModel> models = new HashSet<>();
	
	public AutomobileType (Type type) {
		
		typeId = type.getTypeId();
		typeName = type.getTypeName();
	
	}
	}

