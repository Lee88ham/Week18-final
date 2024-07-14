package automobile.service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import automobile.controller.model.AutomobileEngine;
import automobile.controller.model.AutomobileMake;
import automobile.controller.model.AutomobileModel;
import automobile.controller.model.AutomobileType;
import automobile.dao.EngineDao;
import automobile.dao.MakeDao;
import automobile.dao.ModelDao;
import automobile.dao.TypeDao;
import automobile.entity.Engine;
import automobile.entity.Make;
import automobile.entity.Model;
import automobile.entity.Type;

@Service
public class AutomobileService {
//*************************MAKE METHODS *****************************
	@Autowired
	private MakeDao makeDao;

	@Transactional(readOnly = false)
	public AutomobileMake saveMake(AutomobileMake automobileMake) {
		Long makeId = automobileMake.getMakeId();
		Make make = findOrCreateMake(makeId);

		setFieldInMake(make, automobileMake);
		return new AutomobileMake(makeDao.save(make));
	}

	private void setFieldInMake(Make make, AutomobileMake automobileMake) {
		make.setMakeName(automobileMake.getMakeName());

	}

	private Make findOrCreateMake(Long makeId) {
		Make make;

		if (Objects.isNull(makeId)) {
			make = new Make();
		} else {
			make = findMakeById(makeId);
		}
		return make;
	}

	private Make findMakeById(Long makeId) {
		return makeDao.findById(makeId)
				.orElseThrow(() -> new NoSuchElementException("Make with ID=" + makeId + " was not found."));
	}

	@Transactional(readOnly = true)
	public List<AutomobileMake> retrieveAllMakes() {
		List<Make> makes = makeDao.findAll();
		List<AutomobileMake> response = new LinkedList<>();

		for (Make make : makes) {
			response.add(new AutomobileMake(make));
		}

		return response;
	}

	@Transactional(readOnly = true)
	public AutomobileMake retrieveMakeById(Long makeId) {
		Make make = findMakeById(makeId);
		return new AutomobileMake(make);
	}

	@Transactional(readOnly = false)
	public void deleteMakeById(Long makeId) {
		Make make = findMakeById(makeId);
		makeDao.delete(make);
	}

	// ********************TYPE METHODS***********************************************************
	@Autowired
	private TypeDao typeDao;

	@Transactional(readOnly = false)
	public AutomobileType saveType(AutomobileType automobileType) {
		Long typeId = automobileType.getTypeId();
		Type type = findOrCreateType(typeId);

		setFieldInType(type, automobileType);
		return new AutomobileType(typeDao.save(type));
	}

	private void setFieldInType(Type type, AutomobileType automobileType) {
		type.setTypeName(automobileType.getTypeName());

	}

	private Type findOrCreateType(Long typeId) {
		Type type;

		if (Objects.isNull(typeId)) {
			type = new Type();
		} else {
			type = findTypeById(typeId);
		}
		return type;
	}

	private Type findTypeById(Long typeId) {
		return typeDao.findById(typeId)
				.orElseThrow(() -> new NoSuchElementException(
						"Type with ID=" + typeId + " was not found."));
	}

	@Transactional(readOnly = true)
	public List<AutomobileType> retrieveAllTypes() {
		List<Type> types = typeDao.findAll();
		List<AutomobileType> response = new LinkedList<>();

		for (Type type : types) {
			response.add(new AutomobileType(type));
		}

		return response;
	}

	@Transactional(readOnly = true)
	public AutomobileType retrieveTypeById(Long typeId) {
		Type type = findTypeById(typeId);
		return new AutomobileType(type);
	}

	@Transactional(readOnly = false)
	public void deleteTypeById(Long typeId) {
		Type type = findTypeById(typeId);
		typeDao.delete(type);
	}

	// ********************MODEL METHODS***********************************************************
	@Autowired
	private ModelDao modelDao;

	@Transactional(readOnly = false)
	public AutomobileModel saveModel(AutomobileModel automobileModel, Long makeId, Long typeId) {
		Long modelId = automobileModel.getModelId();
		Make make = findMakeById(makeId);
		Type type = findTypeById(typeId);
		Model model = findOrCreateModel(modelId, typeId, makeId);

		setFieldInModel(model, automobileModel);
		
		model.setMake(make);
		model.setType(type);
		make.getModels().add(model);
		type.getModels().add(model);
		
		return new AutomobileModel(modelDao.save(model));
	}

	private void setFieldInModel(Model model, AutomobileModel automobileModel) {
		model.setModelName(automobileModel.getModelName());
	}

	private Model findOrCreateModel(Long modelId, Long typeId, Long makeId) {

		if (Objects.isNull(modelId)) {
			return new Model();
		} else {
			return findModelById(makeId, modelId, typeId);
		}
	}
private Model findModelById(Long makeId, Long modelId, Long typeId) {
		Model model = modelDao.findById(modelId)
				.orElseThrow(() -> new NoSuchElementException("Model with ID=" + modelId + " was not found."));
		if (model.getMake().getMakeId() != makeId) {
			throw new IllegalArgumentException(
					"The model with this ID=" + modelId + " is not manufactured by this make.");
		}
			if (model.getType().getTypeId() != typeId) {
				throw new IllegalArgumentException(
						"The model with this ID=" + modelId + " is not this type.");
		}
		return model;
}

	@Transactional(readOnly = true)
	public List<AutomobileModel> retrieveAllModels() {
		List<Model> models = modelDao.findAll();
		List<AutomobileModel> response = new LinkedList<>();

		for (Model model : models) {
			response.add(new AutomobileModel(model));
		}

		return response;
	}
	
	@Transactional(readOnly = true)
	public AutomobileModel retrieveModelById(Long modelId) {
		Model model = findModelById(modelId);
		return new AutomobileModel(model);
	}
	private Model findModelById(Long modelId) {
		Model model = modelDao.findById(modelId)
				.orElseThrow(() -> new NoSuchElementException("Model with ID=" + modelId + " was not found."));
		
		return model;
	}
	@Transactional(readOnly = false)
	public void deleteModelById(Long modelId) {
		Model model = findModelById(modelId);
		modelDao.delete(model);		
	}

	// ********************ENGINE METHODS****************************************************
	
	@Autowired
	private EngineDao engineDao;
	
	public AutomobileEngine saveEngine(AutomobileEngine engineData, Long modelId) {

		Model model = findModelById(modelId);
		Long engineId = engineData.getEngineId();
		Engine engine = findOrCreateEngine(engineId, modelId);
		copyEngineFields(engine, engineData);
		model.getEngines().add(engine);
		engine.getModels().add(model);
		return new AutomobileEngine(engineDao.save(engine));
	}

	private void copyEngineFields(Engine engine, AutomobileEngine automobileEngine) {
		engine.setEngineId(automobileEngine.getEngineId());
		engine.setFuelType(automobileEngine.getFuelType());
		engine.setSize(automobileEngine.getSize());
		
	}

	private Engine findOrCreateEngine(Long engineId, Long modelId) {
		if (Objects.isNull(engineId)) {
			return new Engine();
		} else {
			return findEngineById(engineId, modelId);
		}
	}

	private Engine findEngineById(Long engineId, Long modelId) {
		Engine engine = engineDao.findById(engineId)
				.orElseThrow(() -> new NoSuchElementException("Engine with ID=" + engineId + " was not found."));
		boolean found = false;
		
		for(Model model : engine.getModels()) {
			if(model.getModelId() == modelId) {
				found = true;
			break;
			}
		}
		if(!found) {
			throw new IllegalArgumentException(
					"Engine with this ID=" + engineId + " is not availible in this model.");
		}
		return engine;
	}

	@Transactional(readOnly = true)
	public List<AutomobileEngine> retrieveAllEngines() {
		List <Engine> engines = engineDao.findAll();
		List <AutomobileEngine> response = new LinkedList<>();

		for (Engine engine : engines) {
			response.add(new AutomobileEngine(engine));
		}
		return response;
		}

	public AutomobileEngine retrieveEngineById(Long engineId) {
		Engine engine = findEngineById(engineId);
		return new AutomobileEngine(engine);
	}
	
	private Engine findEngineById(Long engineId) {
		Engine engine = engineDao.findById(engineId)
				.orElseThrow(() -> new NoSuchElementException("Engine with ID=" + engineId + " was not found."));
		
		return engine;
	}
	
	public void deleteEngineById(Long engineId) {
		Engine engine = findEngineById(engineId);
		engineDao.delete(engine);			
	}
}
	
	

