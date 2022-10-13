package engine.service;

import engine.model.Buy;

/**
 *  Rename to ICustomerRegistrationService 
 *  create a new folder called interface and move the class in that folder
 */
public interface RegistrationService {
    Buy register(Buy customer) throws Exception;
    void unregister(Long id) throws Exception;
}
