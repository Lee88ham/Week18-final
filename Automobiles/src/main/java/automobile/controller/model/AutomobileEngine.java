package automobile.controller.model;

import automobile.entity.Engine;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AutomobileEngine {

private Long engineId;
private String fuelType;
private String size;
	

	
public AutomobileEngine (Engine engine) {
	
	engineId = engine.getEngineId();
	fuelType = engine.getFuelType();
	size = engine.getSize();
	
	
}
	
}
