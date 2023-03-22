package pro.sky.homework28.exception;

public class WrongEmployeeDataException extends RuntimeException{

    public WrongEmployeeDataException(String message) {
        super(message);
    }

}
