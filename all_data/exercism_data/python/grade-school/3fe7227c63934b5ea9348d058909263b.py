from collections import defaultdict

class School(object):
    def __init__(self, name):
        self._db = defaultdict(set)

    @property
    def db(self):
        return self._db.copy()

    def add(self, name, grade):
        self._db[grade].add(name)

    def grade(self, grade):
        return self._db[grade]

    def sort(self):
        return {
            grade: tuple(sorted(students))
            for grade, students
            in self._db.iteritems()
        }
