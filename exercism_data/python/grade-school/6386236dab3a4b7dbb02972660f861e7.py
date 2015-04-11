__author__ = 'Flavio Miranda'


class School(object):
    def __init__(self, name):
        self.name = name
        self.db = {}

    def add(self, name, grade):
        if grade not in self.db.keys():
            self.db[grade] = self.db.get(grade, {name})
        else:
            self.db[grade] |= {name}

    def grade(self, grd):
        return self.db.get(grd, set())

    def sort(self):
        return {
            grade: tuple(sorted(students)) for grade, students in self.db.items()
        }
