package by.javatr.libproject.controller.command.impl;

import by.javatr.libproject.bean.Author;
import by.javatr.libproject.bean.Book;
import by.javatr.libproject.controller.command.Command;
import by.javatr.libproject.service.BookService;
import by.javatr.libproject.service.exception.ServiceException;
import by.javatr.libproject.service.factory.ServiceFactory;

public class AddBook implements Command {
    @Override
    public String execute(String request) {

        String login = null;
        String name = null;
        Author author = null;

        int id = 0;
        int publish = 0;

        String response = null;

        String[] requestSplit = request.split(";");

        if (!checkSize(requestSplit, 4)) {
            return "invalid input parameters";
        }
        id = Integer.parseInt(requestSplit[0]);
        name = requestSplit[1];
        author = new Author(requestSplit[2]);
        publish = Integer.parseInt(requestSplit[3]);


        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        BookService bookServiceImpl = serviceFactory.getBookService();

        try {
            bookServiceImpl.addBook(new Book(id, name, author, publish));
            response = "Книга " + name + " добавлена";

        } catch (ServiceException e) {
            response = e.getMessage();
        }


        return response;
    }
}
