import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckingAccountTest {


    private CheckingAccount account;

    @BeforeEach
    void setUpAccount() throws IllegalArgumentException {
        account = new CheckingAccount("SA00002", 1000, -100);
    }

    @Test
    @DisplayName("El límite de sobregiro no puede ser positivo")
    void limitOverdraftPositive() {
        assertThrows(IllegalArgumentException.class, () -> new CheckingAccount("SA00002", 1000, 100));
    }

    @Test
    @DisplayName("Podemos depositar dinero")
    void successfulDeposit() throws IllegalArgumentException {
        account.deposit(400);
        assertEquals(1400, account.getBalance());
    }

    @Test
    @DisplayName("Depósito con cantidad negativa lanza una excepción")
    void depositNegativeAmountException() throws IllegalArgumentException {
        assertThrows(IllegalArgumentException.class, () -> account.deposit(-100));
    }

    @Test
    @DisplayName("Retirada con cantidad menor a cero lanza una excepción")
    void withdrawMinusThanZero() {
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(-100));
    }

    @Test
    @DisplayName("Retirada con cantidad cero lanza una excepción")
    void withdrawWithAmountZero() {
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(0));
    }

    @Test
    @DisplayName("Retirada con fondos insuficientes")
    void withdrawWithAmountToBig() {
        // ejemplo: tengo 1000 y el límite es -100 y quiero sacar 1200
        assertThrows(InsufficientFundsException.class, ()->account.withdraw(1200));
    }
}