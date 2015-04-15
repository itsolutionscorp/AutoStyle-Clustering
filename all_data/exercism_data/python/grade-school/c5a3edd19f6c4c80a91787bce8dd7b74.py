from collections import defaultdict

class School:
    def __init__(self, name=None):
        self.name = name
        self._db = defaultdict(set)

    @property
    def db(self):
        return self._db

    def add(self, student, grade):
        self._db[grade].add(student)

    def grade(self, grade):
        return self._db[grade]

    def sort(self):
        return [(grade, tuple(students))
                for grade, students in self._db.items()]
