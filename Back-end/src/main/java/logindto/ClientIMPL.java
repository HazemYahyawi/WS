package logindto;

import com.iset.projetIntegration.CRUD.Models.Client;
import com.iset.projetIntegration.CRUD.Repositories.ClientRepo;
import com.iset.projetIntegration.CRUD.Repositories.ClientSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Optional;
@Service
public class ClientIMPL implements ClientSer {
    @Autowired
    private ClientRepo employeeRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public String addClientAccount(Client employeeDTO) {
        Client employee = new Client(
                employeeDTO.getId(),
                employeeDTO.getNom(),
                employeeDTO.getAdresse(),
                employeeDTO.getCodePostale(),
                employeeDTO.getVille(),
                employeeDTO.getRegion(),
                employeeDTO.getTel(),
                employeeDTO.getCountNum(),
                this.passwordEncoder.encode(employeeDTO.getPwd()),
                employeeDTO.getEmail()

        );
        employeeRepo.save(employee);
        return employee.getNom();
    }
    Client employeeDTO;
    @Override
    public LoginMesage  loginClient(LoginDTO loginDTO) {
        String msg = "";
        Client employee1 = employeeRepo.findByEmail(loginDTO.getEmail());
        if (employee1 != null) {
            String password = loginDTO.getPwd();
            String encodedPassword = employee1.getPwd();
            Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
            if (isPwdRight) {
                Optional<Client> employee = employeeRepo.findOneByEmailAndPassword(loginDTO.getEmail(), encodedPassword);
                if (employee.isPresent()) {
                    return new LoginMesage("Login Success", true);
                } else {
                    return new LoginMesage("Login Failed", false);
                }
            } else {
                return new LoginMesage("password Not Match", false);
            }
        }else {
            return new LoginMesage("Email not exits", false);
        }
    }
}
