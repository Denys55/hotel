package com.ithotel.command;

import java.util.HashMap;
import java.util.Map;

public class CommandContainer {

    private static Map<String, Command> commands;

    private CommandContainer() {
    }

    static{
        commands = new HashMap<>();
        commands.put("login", new LoginComand());
        commands.put("catalog", new CatalogCommand());
        commands.put("createUser", new CreateUserCommand());
        commands.put("updateUser", new UpdateUserCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("order", new OrderCommand());
        commands.put("clientOrders", new ClientOrdersCommand());
        commands.put("paymentOrder", new PaymentOrderCommand());
        commands.put("adminPage", new AdminPageCommand());
        commands.put("personalInformation", new PersonalInformationCommand());
        commands.put("changeOrderByAdmin", new ChangeOrderByAdminCommand());
        commands.put("changeLanguage", new ChangeLanguageCommand());

    }

    public static Command getCommand(String commandName){
        return commands.get(commandName);
    }

}
