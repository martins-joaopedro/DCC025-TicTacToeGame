package br.ufjf;

import java.util.HashSet;
import java.util.Set;

public class Utils {
    

    public static boolean validateCPF(String cpf) {
        
        // Remove possíveis pontos e traços
        cpf = cpf.replaceAll("[^0-9]", "");

        // valida tamanho
        int expectedLength = 11;
        if (cpf.length() != expectedLength)
            return false;

        // valida se são números iguais e também lança exceção
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < expectedLength; i++) {

            if (!Character.isDigit(cpf.charAt(i))) {
                throw new IllegalArgumentException("CPF contém caracteres inválidos.");
            }

            set.add(cpf.charAt(i));
        }
        if (set.size() == 1)
            return false;

        // valida dígitos verificadores
        int digit10 = calcSumAndReturnTheDigit(10, cpf);
        int digit11 = calcSumAndReturnTheDigit(11, cpf);

        // Retorna o resultado da validação
        return digit10 == (cpf.charAt(9) - '0') && digit11 == (cpf.charAt(10) - '0');
    }

    public static int calcSumAndReturnTheDigit(int value, String cpf) {
        int sum = 0;
        int weight = value; 

        for (int i = 0; i < value - 1; i++) {
            int digit = cpf.charAt(i) - '0';
            sum += (digit * weight);
            weight--;
        }

        int r = 11 - (sum % 11);
        if (r == 10 || r == 11)
            return 0;
        return r;
    }

    public static boolean validateBirthDate(String cpf) {
        return true;
    }

    public static String formatCPF(String cpf) {
        return (cpf.substring(0, 3)) + "." + cpf.substring(3, 6) + "." 
        + cpf.substring(6, 9) + "-" + cpf.substring(9, 11);
    }
}
