package wj.test.httpclient.model;

import lombok.Data;

@Data
public class GetUserInfoCase {

    private int userId;
    private String expected;
}
