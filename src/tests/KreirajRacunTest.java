package tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import codes.KreirajRacun;

public class KreirajRacunTest {

	KreirajRacun test;
	KreirajRacun korisnik1;
	KreirajRacun korisnik2;
	boolean provjera;
	
	@Before
	public void setUp() throws IOException{
		test = new KreirajRacun(10, "Test", 100);
		korisnik1 = new KreirajRacun(20, "Korisnik1", 100);
		korisnik2 = new KreirajRacun(30, "korisnik2", 100);
	}
	
	@Test
	public void shouldReturnFalseIfAccNumberIsLessThan0() {
		//act
		//assert
		assertFalse(KreirajRacun.validacijaBrojaRacuna(-5));
		
	}
	
	@Test
	public void shouldReturnFalseIfAccNumberAlreadyExistsWhenCreatingAcc() {
		assertFalse(KreirajRacun.provjeraDuplikataBrojaRacuna(10));
	}
	
	@Test
	public void shouldReturnFalseIfAmountForDepositIsLessThan0WhenCreatingAcc() {
		assertFalse(KreirajRacun.provjeraIznosa(-5));
	}
	
	@Test
	public void shouldReturnFalseIfSourceAccDoesNotExistsWhenTransferingMoney() {
		assertFalse(KreirajRacun.provjeraPostojanjaSourceRacuna(0));
	}
	
	@Test
	public void shouldReturnFalseIfTargetAccDoesNotExistsWhenTransferingMoney() {
		assertFalse(KreirajRacun.provjeraPostojanjaTargetRacuna(0));
	}
	
	@Test
	public void shouldReturnFalseIfFundsOnSourceAccAreNotEnoughWhenTransferingMoney() {
		assertFalse(KreirajRacun.provjeraIznosaNaSourceAcc(300));
	}
	
	@Test
	public void shouldReturnTrueIfTransferingMoneyIsCompletedWhenAllConditionsAreMet() {
		assertTrue(KreirajRacun.transferNovca(10, 20, 50));
	}
	
	@Test
	public void shouldReturnFalseIfTransferingMoneyIsNotCompletedWhenCalled() {
		assertFalse(KreirajRacun.transferNovca(40, 60, 50000));
	}
	
	@Test
	public void shouldReturnTrueIfAccNumExistsWhenShowDetailsOfAccMethodIsCalled() {
		assertTrue(KreirajRacun.ispisRacuna(10));
	}
	
	@Test
	public void shouldReturnFalseIfAccNumDoesNotExistsWhenShowDetailsOfAccMethodIsCalled() {
		assertFalse(KreirajRacun.ispisRacuna(40));
	}
}
