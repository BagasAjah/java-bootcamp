package com.mitrais.service;

import java.io.IOException;
import java.util.Scanner;

public interface IService {
    void display() throws IOException;
    boolean process(Scanner scanner);
}
