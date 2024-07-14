package automobile.controller.model;

import java.util.HashSet;
import java.util.Set;

import automobile.entity.Engine;
import automobile.entity.Model;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AutomobileModel {

	private Long modelId;
	private String modelName;
	private Set<AutomobileEngine> engines = new HashSet<>();

	

	public AutomobileModel(Model model) {
		modelId = model.getModelId();
		modelName = model.getModelName();

		for (Engine engine : model.getEngines()) {
			engines.add(new AutomobileEngine(engine));
		}
	}

}
