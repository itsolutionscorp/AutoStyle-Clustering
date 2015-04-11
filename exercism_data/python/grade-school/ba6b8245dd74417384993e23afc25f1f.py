from collections import OrderedDict


class School(object):

    """A school with students."""

    def __init__(self, name):
        """Create new school with given name."""
        self.name = name
        self.__db = {}

    @property
    def db(self):
        """Return a copy of the student database."""
        return {grade: students.copy() for
                grade, students in self.__db.items()}

    def add(self, name, grade):
        """Add a student with given name and grade."""
        if not grade in self.__db.keys():
            self.__db[grade] = set()
        self.__db[grade].add(name)

    def grade(self, grade):
        """Return a set of all students in given grade."""
        if grade in self.__db.keys():
            return self.__db[grade].copy()
        else:
            return set()

    def sort(self):
        """Return an ordered version of the student database."""
        return OrderedDict(sorted([(grade, tuple(sorted(students))) for
                                   grade, students in self.__db.items()]))
