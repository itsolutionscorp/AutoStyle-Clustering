class AccessError(Exception):
    pass


class School:
    def __init__(self, name):
        self.name = name
        self.__db = {}

    @property
    def db(self):
        return self.__db

    @db.setter
    def db(self, value):
        raise AccessError('Cannot edit db structure directly.')

    def add(self, name, grade):
        curr_set = self.__db.get(grade, set())
        curr_set.add(name)
        self.__db[grade] = curr_set

    def grade(self, grade):
        return self.__db.get(grade, set())

    def sort(self):
        return {key: tuple(sorted(self.__db[key])) for key in self.__db}
