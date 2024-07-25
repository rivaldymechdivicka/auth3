package com.tujuhsembilan.example.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;
import java.util.Set;

@Service
public class TokenService  {

    private final Map<String, String> tokenStore = new ConcurrentHashMap<>(); // Token to username mapping
    private final Map<String, Set<String>> userTokens = new ConcurrentHashMap<>(); // Username to token set mapping

  
    public void storeToken(String token, String username) {
        tokenStore.put(token, username);
        userTokens.computeIfAbsent(username, k -> ConcurrentHashMap.newKeySet()).add(token);
    }

   
    public void invalidateToken(String token) {
        String username = tokenStore.remove(token);
        if (username != null) {
            userTokens.getOrDefault(username, Set.of()).remove(token);
        }
    }

   
    public void invalidateUserTokens(String username) {
        Set<String> tokens = userTokens.remove(username);
        if (tokens != null) {
            tokens.forEach(tokenStore::remove);
        }
    }

  
    public boolean isTokenValid(String token) {
        return tokenStore.containsKey(token);
    }
}
