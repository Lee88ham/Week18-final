package automobile.controller.model;

import java.util.HashSet;
import java.util.Set;

import automobile.entity.Make;
import automobile.entity.Model;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AutomobileMake {
	private Long makeId;
	private String makeName;
	private Set<AutomobileModel> models = new HashSet<>();
			
	
public AutomobileMake(Make make) {
	makeId = make.getMakeId();
	makeName = make.getMakeName();
	
	for(Model model : make.getModels()) {
		models.add(new AutomobileModel(model));
}
}
}
