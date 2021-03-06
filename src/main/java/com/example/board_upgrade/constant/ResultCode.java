package com.example.board_upgrade.constant;

public enum ResultCode {

    Success(0, "성공"),


    //유저 관련 에러 100번대
    NOT_ALLOW_USER(100, "허가되지 않은 유저입니다."),
    UNKNOWN_USER(101, "존재하지 않는 유저입니다."),
    PASSWORD_NOT_CORRECT(102, "비밀번호가 일치하지 않습니다."),
    USER_ALREADY_EXISTS(103,"이미 가입된 회원입니다."),
    NAME_ALREADY_EXISTS(104,"이미 존재하는 닉네임입니다."),


    //글 관련 오류 200번대
    FAIL_TO_REGISTER_BOARD(200, "글 올리는것을 실패했습니다."),
    FAIL_TO_GET_BOARD(201,"글 가져오기를 실패했습니다."),
    FAIL_TO_DELETE_BOARD(202,"이미 삭제된 글입니다."),

    //글 올리기 관련 오류 300번대
    FAIL_TO_REGISET_COMMENT(300, "댓글다는것을 실패했습니다."),

    //좋아요 관련 오류 400번대
    //세션이 만료되었을때. 로그인 화면으로 리다이렉트하기.
    //이미 좋아요한 게시물일때.
    FAIL_TO_LIKE_NO_SESSION(400,"다시 로그인 해주세요."),
    ALREADY_LIKE_BOARD(401,"이미 좋아요 한 게시물 입니다."),


    //기타 에러
    DB_ERROR(9998, "DB 오류입니다."),
    ETC_ERROR(9999, "시스템 오류입니다.");

    private int code;
    private String message;

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public <T> Result<T> result(T resultObject) {
        return new Result<T>(resultObject, this);
    }

    public <T> Result<T> result() { return new Result<T>(null, this); }

    @Override
    public String toString() {
        return "{code=" + code + ", message=" + message + "}";
    }
}
