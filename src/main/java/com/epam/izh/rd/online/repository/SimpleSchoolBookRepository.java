package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.SchoolBook;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {

    private SchoolBook[] schoolBooks = new SchoolBook[0];

    /**
     * Метод должен сохранять школьную книгу в массив schoolBooks.
     * Массив при каждом сохранении должен увеличиваться в размере ровно на 1.
     * То есть он ровно того размера, сколько сущностей мы в него сохранили.
     * <p>
     * Одну и ту же книгу МОЖНО сохранить в массиве несколько раз, проверки на то, что такая книга уже сохранена, делать не нужно.
     * <p>
     * Если сохранение прошло успешно, метод должен вернуть true.
     */
    public boolean save(SchoolBook book) {
        SchoolBook[] tempSchoolBooks = new SchoolBook[schoolBooks.length + 1];
        System.arraycopy(schoolBooks, 0, tempSchoolBooks, 0, schoolBooks.length);
        tempSchoolBooks[tempSchoolBooks.length - 1] = book;
        schoolBooks = tempSchoolBooks;
        return true;
    }

    /**
     * Метод должен находить в массиве schoolBooks все книги по имени.
     * <p>
     * Если книги найдены - метод должен их вернуть.
     * Если найденных по имени книг нет, должен вернуться пустой массив.
     */
    public SchoolBook[] findByName(String name) {
        SchoolBook[] foundBooks = new SchoolBook[0];
        for (SchoolBook currentBook : schoolBooks) {
            if (currentBook.getName() == name) {
                SchoolBook[] tempFoundBooks = new SchoolBook[foundBooks.length + 1];
                System.arraycopy(foundBooks, 0, tempFoundBooks, 0, foundBooks.length);
                tempFoundBooks[tempFoundBooks.length - 1] = currentBook;
                foundBooks = tempFoundBooks;
            }
        }
        return foundBooks;
    }

    /**
     * Метод должен удалять книги из массива schoolBooks по названию.
     * Если книг с одинаковым названием в массиве несколько, метод должен удалить их все.
     * <p>
     * Важно: при удалении книги из массива размер массива должен уменьшиться!
     * То есть, если мы сохранили 2 разные книги и вызвали count() (метод ниже), то он должен вернуть 2.
     * Если после этого мы удалили 1 книгу, метод count() должен вернуть 1.
     * <p>
     * Если хотя бы одна книга была найдена и удалена, метод должен вернуть true, в противном случае,
     * если книга не была найдена, метод должен вернуть false.
     */
    public boolean removeByName(String name) {
        SchoolBook[] foundBooks = findByName(name);
        if (foundBooks.length == 0) {
            return false;
        }
        SchoolBook[] leftSchoolBooks = new SchoolBook[0];
        int i;
        int j;
        int addedBooksCount = 0;
        boolean currentSchoolBookIsFound = false;
        for (i = 0; i < schoolBooks.length; i++) {
            for (j = 0; j < foundBooks.length; j++) {
                if (foundBooks[j].equals(schoolBooks[i])) {
                    currentSchoolBookIsFound = true;
                    break;
                }
            }
            if (currentSchoolBookIsFound == false) {
                SchoolBook[] tempLeftSchoolBooks = new SchoolBook[leftSchoolBooks.length + 1];
                tempLeftSchoolBooks[addedBooksCount] = schoolBooks[i];
                leftSchoolBooks = tempLeftSchoolBooks;
                addedBooksCount++;
            }
            currentSchoolBookIsFound = false;
        }
        schoolBooks = leftSchoolBooks;
        return true;
    }

    /**
     * Метод возвращает количество сохраненных сущностей в массиве schoolBooks.
     */
    public int count() {
        return schoolBooks.length;
    }
}
