package com.luannycholas.models;

import java.io.Serializable;
public record Credentials(
    String email,
    String password
) implements Serializable { }