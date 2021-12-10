package com.ithotel.util;

public class QuerySQL {
    public static final String INSERT_USER = "INSERT INTO user(id, login, password) VALUES (DEFAULT, ?, ?)";
    public static final String GET_USER_BY_ID = "SELECT * FROM user WHERE id=?";
    public static final String GET_USER_BY_LOGIN = "SELECT * FROM user WHERE login=?";

    public static final String GET_ALL_ROOM = "SELECT * FROM room";
    public static final String GET_ALL_ROOM_LIMIT = "SELECT * FROM room limit ?, ?";
    public static final String SORT_ROOM = "SELECT * FROM room ORDER BY ";
    public static final String LIMIT = " limit ?, ?";

    public static final String COUNT_OF_ROOM = "SELECT COUNT(1) FROM room";

    public static final String INSERT_PERSONAL_INFORMATION_BY_ID_USER = "INSERT INTO personal_information(id, firstname, lastname, email, phone, user_id) " +
            "VALUES(DEFAULT, ?, ?, ?, ?, ?)";

    public static final String GET_PERSONAL_INFORMATION_BY_USER_ID = "SELECT firstname, lastname, phone, email FROM personal_information WHERE user_id=?";

    public static final String UPDATE_USER_BY_ID = "UPDATE user SET login=?, password=? WHERE id=?";
    public static final String UPDATE_PERSONAL_INFORMATION_BY_USER_ID = "UPDATE personal_information SET firstname=?, lastname=?, phone=?, email=? WHERE user_id=?";
    public static final String UPDATE_USER_BALANCE_BY_ID = "update user set balance=balance + ? where id=?";

    public static final String INSERT_ORDER = "INSERT INTO ithotel.order(id, check_in, check_out, class_id, statuses_order_id, number_place) VALUES (default, ?, ?, ?, ?, ?)";
    public static final String INSERT_ORDER_AND_USER = "INSERT INTO users_orders (order_id, user_id) VALUES (?, ?)";
    public static final String INSERT_ORDER_AND_ROOM = "INSERT INTO order_has_room (order_id, room_id) VALUES (?, ?)";
    public static final String UPDATE_STATUS_OF_ROOM = "UPDATE room SET statuses_room_id=? where id=?";

    public static final String INSERT_ORDER_FOR_MAKE_ORDER_FROM_PROFILE =
            "insert into ithotel.order(id, number_place, check_in, check_out, class_id, statuses_order_id) value(default, ?, ?, ?, ?, ?)";

    public static final String FIND_ORDER_STATUS_INVOICED_BY_USER_ID = "select * from ithotel.order o " +
            "join users_orders uo on uo.user_id=? and o.id = uo.order_id " +
            "where o.statuses_order_id=3";

    public static final String SUBTRACK_USER_BALANCE = "UPDATE user SET balance=balance-? where id=?";
    public static final String UPDATE_ORDER_STATUS_ORDER = "UPDATE ithotel.order SET statuses_order_id=? where id =?";

    public static final String FIND_ROOM_ID_BY_ORDER_ID = "SELECT room_id FROM order_has_room WHERE order_id=?";

    public static final String GET_ALL_ORDERS = "SELECT * FROM ithotel.order o " +
            "inner join order_has_room ohr on o.id=ohr.order_id";
    public static final String GET_ORDERS_BY_ORDERS_STATUS = "SELECT * FROM ithotel.order o " +
            "inner join order_has_room ohr on ohr.order_id=o.id " +
            "where statuses_order_id =?";
    public static final String GET_PERSONAL_INFORMATION_BY_ORDER_ID = "select * from personal_information pi " +
            "inner join users_orders uo on uo.user_id=pi.user_id " +
            "where uo.order_id=?";
    public static final String GET_ALL_ID_ROOMS = "SELECT id FROM room;";
    public static final String GET_ORDER_BY_ID = "SELECT * FROM ithotel.order o " +
            " inner join order_has_room ohs on o.id = ohs.order_id " +
            " where id=?";
    public static final String GET_ROOM_BY_ID = "SELECT * FROM room where id=?";
    public static final String UPDATE_ORDER_BY_ID = "UPDATE ithotel.order SET statuses_order_id=?, check_in=?, check_out=?, " +
            "total_cost=?, class_id=?, number_place=?, time_order=? WHERE id=?";
    public static final String UPDATE_ORDER_HAS_ROOM_ROOM_ID_BY_ORDER_ID = "update order_has_room SET room_id=? where order_id=?";
    public static final String UPDATE_STATUSROOM_IN_ROOM_BY_ID = "UPDATE room SET statuses_room_id=? WHERE id=?";
    public static final String  CHECK_PERSONAL_INFORMATION_BY_EMAIL = "SELECT * FROM personal_information WHERE email=?";
    public static final String  CHECK_PERSONAL_INFORMATION_BY_PHONE = "SELECT * FROM personal_information WHERE phone=?";
}
