package kr.ac.skuniv.cosmos.exception;

public class WrongPasswordException extends BusinessLogicException {
    public WrongPasswordException() {
        super(ErrorCodeType.WRONG_PASSWORD);
    }
}
