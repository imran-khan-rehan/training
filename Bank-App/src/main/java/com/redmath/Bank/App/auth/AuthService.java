package com.redmath.Bank.App.auth;

import com.redmath.Bank.App.Balance.BalanceService;
import com.redmath.Bank.App.Jwt.JwtUtil;
import com.redmath.Bank.App.User.User;
import com.redmath.Bank.App.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private BalanceService balanceService; // Inject BalanceService

    private static final int UNAUTHORIZED_STATUS = HttpStatus.UNAUTHORIZED.value();

    public ResponseEntity<?> authenticate(AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(UNAUTHORIZED_STATUS).body(new AuthResponse("password is wrong"));
        }
        User user = userRepository.findByEmail(authRequest.getEmail());
        return ResponseEntity.ok(new AuthResponse(jwtUtil.generateToken(authRequest.getEmail()), user.getId(), user.getRole()));
    }

    public String registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        balanceService.createBalanceForUser(user.getEmail(), 0.0); // Set initial balance amount as needed

        return jwtUtil.generateToken(user.getEmail());
    }

}
