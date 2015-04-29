class School:
    def __init__(self, name):
        self.name = name
        self.__db = {}

    def add(self, name, grade):
        self.__db.setdefault(grade, set()).add(name)

    @property
    def db(self):
        return self.__db

    def grade(self, grade):
        return self.__db.get(grade, set())

    def sort(self):
        return {grade: tuple(sorted(students))
                for grade, students in self.db.items()}
