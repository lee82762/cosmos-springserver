package kr.ac.skuniv.cosmos.exception;

public class DuplicatedEmailException extends BusinessLogicException {
    public DuplicatedEmailException() {
        super(ErrorCodeType.DUPLICATED_EMAIL);
    }
}
