package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;

public class SimpleAuthorRepository implements AuthorRepository {

    private Author[] authors = new Author[0];

    /**
     * Метод должен сохранять автора в массив authors.
     * Массив при каждом сохранении должен увеличиваться в размере ровно на 1.
     * То есть он ровно того размера, сколько сущностей мы в него сохранили.
     * <p>
     * Если на вход для сохранения приходит автор, который уже есть в массиве (сохранен), то автор не сохраняется и
     * метод возвращает false.
     * <p>
     * Можно сравнивать только по полному имени (имя и фамилия), считаем, что двух авторов
     * с одинаковыми именем и фамилией быть не может.
     * Подсказка - можно использовать для проверки метод findByFullName.
     * <p>
     * Если сохранение прошло успешно, метод должен вернуть true.
     */
    public boolean save(Author author) {
        if (findByFullName(author.getName(), author.getLastName()) != null) {
            return false;
        }
        Author[] tempAuthors = new Author[authors.length + 1];
        System.arraycopy(authors, 0, tempAuthors, 0, authors.length);
        tempAuthors[tempAuthors.length - 1] = author;
        authors = tempAuthors;
        return true;
    }

    /**
     * Метод должен находить в массиве authors автора по имени и фамилии (считаем, что двух авторов
     * с одинаковыми именем и фамилией быть не может.)
     * <p>
     * Если автор с таким именем и фамилией найден - возвращаем его, если же не найден, метод должен вернуть null.
     */
    public Author findByFullName(String name, String lastname) {
        for (Author currentAuthor : authors) {
            if (currentAuthor.getName() == name && currentAuthor.getLastName() == lastname) {
                return currentAuthor;
            }
        }
        return null;
    }

    /**
     * Метод должен удалять автора из массива authors, если он там имеется.
     * Автора опять же, можно определять только по совпадению имении и фамилии.
     * <p>
     * Важно: при удалении автора из массива размер массива должен уменьшиться!
     * То есть, если мы сохранили 2 авторов и вызвали count() (метод ниже), то он должен вернуть 2.
     * Если после этого мы удалили 1 автора, метод count() должен вернуть 1.
     * <p>
     * Если автор был найден и удален, метод должен вернуть true, в противном случае, если автор не был найден, метод
     * должен вернуть false.
     */
    public boolean remove(Author author) {
        Author foundAuthor = findByFullName(author.getName(), author.getLastName());
        if (foundAuthor == null) {
            return false;
        }
        int i;
        int indexOfFoundAuthor = 0;
        for (i = 0; i < authors.length; i++) {
            if (authors[i].equals(foundAuthor)) {
                indexOfFoundAuthor = i;
                break;
            }
        }
        Author[] tempAuthors = new Author[authors.length - 1];
        System.arraycopy(authors, 0, tempAuthors, 0, indexOfFoundAuthor);
        System.arraycopy(authors, indexOfFoundAuthor + 1, tempAuthors, indexOfFoundAuthor, tempAuthors.length - indexOfFoundAuthor);
        authors = tempAuthors;
        return true;
    }

    /**
     * Метод возвращает количество сохраненных сущностей в массиве authors.
     */
    public int count() {
        return authors.length;
    }

}
