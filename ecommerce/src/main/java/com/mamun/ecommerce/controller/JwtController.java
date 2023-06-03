package com.mamun.ecommerce.controller;


import com.mamun.ecommerce.payload.JwtRequest;
import com.mamun.ecommerce.payload.JwtResponse;
import com.mamun.ecommerce.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
//generating token and it is first time in JWT implementation
@RestController
public class JwtController {


    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/token")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {

         //at first validate the jwtreuest(username and pass)
       try {
           this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUserName(),jwtRequest.getPassword()));
       }
       catch (UsernameNotFoundException e){
           e.printStackTrace();
           throw new Exception("Bad credential");
       }

       //user authenticated

        //now get the user details from userdetailsservice


        UserDetails userDetails= this.userDetailsService.loadUserByUsername(jwtRequest.getUserName());

       //generate token

      String token=  this.jwtUtil.generateToken(userDetails);
        System.out.println(token);
//got token now we have to send the token to client
      return ResponseEntity.ok(new JwtResponse(token));



    }
}
