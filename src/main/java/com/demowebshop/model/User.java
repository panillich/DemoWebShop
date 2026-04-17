package com.demowebshop.model;

public record User(Gender gender, String firstName, String lastName, String email, String password) {
}