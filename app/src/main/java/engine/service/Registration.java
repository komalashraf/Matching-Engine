package engine.service;

import engine.model.Buy;

public class Registration implements RegistrationService {
    private CusttomerRepo repo;

    Registration(CusttomerRepo repo){
        this.repo = repo;
    }

    @Override
    public Buy register(Buy customer) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void unregister(Long id) throws Exception {
        // TODO Auto-generated method stub
        
    }
    
}
