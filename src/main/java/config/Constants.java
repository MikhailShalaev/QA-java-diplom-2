package config;

public class Constants {
    public static final String BASE_URL = "https://stellarburgers.nomoreparties.site";

    public static final String CREATE_USER_EP = "/api/auth/register";
    public static final String LOGIN_USER_EP = "/api/auth/login";
    public static final String DELETE_USER_EP = "/api/auth/user";
    public static final String UPDATE_USER_EP = "/api/auth/user";

    public static final String CREATE_ORDER_EP = "/api/orders";
    public static final String GET_INGREDIENTS_EP = "/api/ingredients";
    public static final String GET_USER_ORDERS_EP = "/api/orders";

    public static final int OK = 200;
    public static final int UNAUTHORIZED = 401;
    public static final int BAD_REQUEST = 400;
    public static final int SERVER_ERROR = 500;
    public static final int FORBIDDEN = 403;
    public static final String SUCCESS = "success";
    public static final String MESSAGE = "message";
    public static final String EMAIL = "user.email";
    public static final String NAME = "user.name";
    public static final String ORDERS = "orders";

}
