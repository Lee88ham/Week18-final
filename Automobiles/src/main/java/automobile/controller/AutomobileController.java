package automobile.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import automobile.controller.model.AutomobileEngine;
import automobile.controller.model.AutomobileMake;
import automobile.controller.model.AutomobileModel;
import automobile.controller.model.AutomobileType;
import automobile.service.AutomobileService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/automobile")
@Slf4j
public class AutomobileController {
	@Autowired
	private AutomobileService automobileService;

	// ********************MAKE METHODS***********************************************************
	@PostMapping("/make")
	@ResponseStatus(code = HttpStatus.CREATED)
	public AutomobileMake insertMake(@RequestBody AutomobileMake automobileMake) {
		log.info("Creating make {}", automobileMake);
		return automobileService.saveMake(automobileMake);
	}

	@PutMapping("/make/{makeId}")
	public AutomobileMake updateMake(@PathVariable long makeId, @RequestBody AutomobileMake automobileMake) {
		automobileMake.setMakeId(makeId);
		log.info("Updating make {}", automobileMake);
		return automobileService.saveMake(automobileMake);

	}

	@GetMapping("/make")
	public List<AutomobileMake> retrieveAllMakes() {
		log.info("Retreive all makes called.");
		return automobileService.retrieveAllMakes();
	}

	@GetMapping("/make/{makeId}")
	public AutomobileMake retrieveMakeById(@PathVariable Long makeId) {
		log.info("Retrieving make with ID={}", makeId);
		return automobileService.retrieveMakeById(makeId);
	}

	@DeleteMapping("/make")
	public void deleteAllMakes() {
		log.info("Attempting to delete all makes");
		throw new UnsupportedOperationException("Deleting all makes is not allowed.");
	}

	@DeleteMapping("/make/{makeId}")
	public Map<String, String> deleteMakeById(@PathVariable Long makeId) {
		log.info("Deleting make with ID={}", makeId);

		automobileService.deleteMakeById(makeId);
		return Map.of("message", "Deletion of make with ID=" + makeId + " was successful.");
	}

	// ********************TYPE METHODS***********************************************************
	@PostMapping("/type")
	@ResponseStatus(code = HttpStatus.CREATED)
	public AutomobileType insertType(@RequestBody AutomobileType automobileType) {
		log.info("Creating type {}", automobileType);
		return automobileService.saveType(automobileType);
	}

	@PutMapping("/type/{typeId}")
	public AutomobileType updateType(@PathVariable long typeId, @RequestBody AutomobileType automobileType) {
		automobileType.setTypeId(typeId);
		log.info("Updating type {}", automobileType);
		return automobileService.saveType(automobileType);

	}

	@GetMapping("/type")
	public List<AutomobileType> retrieveAllTypes() {
		log.info("Retreive all types called.");
		return automobileService.retrieveAllTypes();
	}

	@GetMapping("/type/{typeId}")
	public AutomobileType retrieveTypeById(@PathVariable Long typeId) {
		log.info("Retrieving type with ID={}", typeId);
		return automobileService.retrieveTypeById(typeId);
	}

	@DeleteMapping("/type")
	public void deleteAllTypes() {
		log.info("Attempting to delete all types");
		throw new UnsupportedOperationException("Deleting all types is not allowed.");
	}

	@DeleteMapping("/type/{typeId}")
	public Map<String, String> deleteTypeById(@PathVariable Long typeId) {
		log.info("Deleting type with ID={}", typeId);

		automobileService.deleteTypeById(typeId);
		return Map.of("message", "Deletion of type with ID=" + typeId + " was successful.");
	}

	// ********************MODEL METHODS***********************************************************
	@PostMapping("/make/{makeId}/type/{typeId}/model")
	@ResponseStatus(code = HttpStatus.CREATED)
	public AutomobileModel insertModel(@RequestBody AutomobileModel automobileModel, @PathVariable Long makeId,
			@PathVariable Long typeId) {

		log.info("Adding model {} for make with ID= {}", automobileModel);
		return automobileService.saveModel(automobileModel, makeId, typeId);
	}

	@PutMapping("/make/{makeId}/type/{typeId}/model/{modelId}")
	public AutomobileModel updateModel(@RequestBody AutomobileModel automobileModel, @PathVariable Long makeId,
			@PathVariable Long typeId, @PathVariable Long modelId) {
		automobileModel.setModelId(modelId);
		log.info("Updating model {}", automobileModel);
		return automobileService.saveModel(automobileModel,makeId, typeId);
	}

	@GetMapping("/model")
	public List<AutomobileModel> retrieveAllModels() {
		log.info("Retreive all models called.");
		return automobileService.retrieveAllModels();
	}

	@GetMapping("/model/{modelId}")
	public AutomobileModel retrieveModelById(@PathVariable Long modelId) {
		log.info("Retrieving model with ID={}", modelId);
		return automobileService.retrieveModelById(modelId);
	}

	@DeleteMapping("/model")
	public void deleteAllModels() {
		log.info("Attempting to delete all models");
		throw new UnsupportedOperationException("Deleting all models is not allowed.");
	}

	@DeleteMapping("/model/{modelId}")
	public Map<String, String> deletemodelById(@PathVariable Long modelId) {
		log.info("Deleting model with ID={}", modelId);

		automobileService.deleteModelById(modelId);
		return Map.of("message", "Deletion of model with ID=" + modelId + " was successful.");
	}
	// ********************ENGINE METHODS***********************************************************

	@PostMapping("/model/{modelId}/engine")
	@ResponseStatus(code = HttpStatus.CREATED)
	public AutomobileEngine insertEngine(@RequestBody AutomobileEngine automobileEngine, @PathVariable Long modelId) {

		log.info("Creating engine={} for model={}", automobileEngine, modelId);

		return automobileService.saveEngine(automobileEngine, modelId);
	}

	@PutMapping("/model/{modelId}/engine/{engineId}")
	public AutomobileEngine modifyEngine(@PathVariable Long engineId, @RequestBody AutomobileEngine automobileEngine, @PathVariable Long modelId) {

		automobileEngine.setEngineId(engineId);

		log.info("Modifying pet store with " + automobileEngine);

		return automobileService.saveEngine(automobileEngine, modelId);
	}

	@GetMapping("/engine")
	public List<AutomobileEngine> retrieveAllEngines() {
		log.info("Retrieve all engines");
		return automobileService.retrieveAllEngines();
	}

	@GetMapping("/engine/{engineId}")
	public AutomobileEngine retrieveEngineById(@PathVariable Long engineId) {
		log.info("Retrieveing engine with ID={} ", engineId);

		return automobileService.retrieveEngineById(engineId);
	}

	@DeleteMapping("/engine")
	public void deleteAllEngines() {
		log.info("Attempting to delete all engines");
		throw new UnsupportedOperationException("Deleting all engines is not allowed.");
	}

	@DeleteMapping("/engine/{engineId}")
	public Map<String, String> deleteEngineById(@PathVariable Long engineId) {
		log.info("Deleting engine by ID={}", engineId);
		automobileService.deleteEngineById(engineId);

		return Map.of("message", "Deletion of engine with ID=" + engineId + " was successful.");
	}

}
