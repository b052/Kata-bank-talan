import com.abdou.kataAccountBank.AccountBankServiceImpl;
import com.abdou.kataAccountBank.IAccountBankService;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AccountBankServiceTest {
    
    private IAccountBankService iAccountBankService;
    
    @Before
    public void setUp(){
        this.iAccountBankService = new AccountBankServiceImpl();
    }
    @Test
    public void must_have_0__into_balance_in_start() {
        assertEquals(0, iAccountBankService.getBalance());
    }

    @Test
    public void must_have_100_into_balance_when_depositing_100() {
        //GIVEN
        //WHEN
        this.iAccountBankService.depositOperation(100);
        //THEN
        assertEquals(100, this.iAccountBankService.getBalance());
    }

    @Test
    public void must_have_30_into_balance_when_deposit_50_and_withdrawal_20() {
        //GIVEN
        //WHEN
        this.iAccountBankService.depositOperation(50);
        this.iAccountBankService.withdrawalOperation(20);
        //THEN
        assertEquals(30, this.iAccountBankService.getBalance());
    }

    @Test
    public void must_have_0_into_balance_when_deposit_20_and_withdrawal_20() {
        //GIVEN
        //WHEN
        this.iAccountBankService.depositOperation(20);
        this.iAccountBankService.withdrawalOperation(20);
        //THEN
        assertEquals(0, this.iAccountBankService.getBalance());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void must_throw_UnsupportedOperationException_when_deposit_1000_and_withdrawal_2000() {
        this.iAccountBankService.depositOperation(1000);
        this.iAccountBankService.withdrawalOperation(2000);
    }

    @Test
    public void must_have_20_into_Balance_when_i_do_2_deposits_of_10_and_10_consecutively() {
        //GIVEN
        //WHEN
        this.iAccountBankService.depositOperation(10);
        this.iAccountBankService.depositOperation(10);
        //THEN
        assertEquals(20, this.iAccountBankService.getBalance());
    }

    @Test
    public void must_print_1_Operation_when_depositing_60_and_printing_Operation() {
        //GIVEN
        //WHEN
        this.iAccountBankService.depositOperation(60);
        String result = this.iAccountBankService.historyOperations();
        //THEN
        assertTrue(result.contains(this.OperationsDetails("DEPOSIT", 60, 60)));
    }

    @Test
    public void must_print_many_operations_as_deposits_and_withdrawals_made() {
        //GIVEN
        //WHEN
        this.iAccountBankService.depositOperation(60);
        this.iAccountBankService.withdrawalOperation(10);
        String result = this.iAccountBankService.historyOperations();
        //THEN
        assertTrue(result.contains(this.OperationsDetails("DEPOSIT", 60, 60)));
        assertTrue(result.contains(this.OperationsDetails("WITHDRAWAL", 10, 50)));
    }

    @Test
    public void print_5_OperationsDetails_when_do_5_OperationsDetails() {
        //GIVEN
        //WHEN
        this.iAccountBankService.depositOperation(50);
        this.iAccountBankService.withdrawalOperation(20);
        this.iAccountBankService.withdrawalOperation(30);
        this.iAccountBankService.depositOperation(10);
        this.iAccountBankService.depositOperation(20);
        String result = this.iAccountBankService.historyOperations();
        //THEN
        assertTrue(result.contains(this.OperationsDetails("DEPOSIT", 50, 50)));
        assertTrue(result.contains(this.OperationsDetails("WITHDRAWAL", 20, 30)));
        assertTrue(result.contains(this.OperationsDetails("WITHDRAWAL", 30, 0)));
        assertTrue(result.contains(this.OperationsDetails("DEPOSIT", 10, 10)));
        assertTrue(result.contains(this.OperationsDetails("DEPOSIT", 20, 30)));
    }


    private String OperationsDetails(String operation, int amount, int balance) {
        return String.format("%s of %d - DATE : %s - BALANCE : %d", operation, amount, new SimpleDateFormat("yyyy-MM-dd").format(new Date()), balance);
    }
}
