package pl.rysicz.erservicerest;

public class FuncUnhandledException extends RuntimeException {

    FuncUnhandledException(String base) {
        super("Functionality of getting currency rates for base currency other than PLN has not been handled yet. Invalid base currency parameter" + base);
    }
}
