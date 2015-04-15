from collections import defaultdict


class School:
    def __init__(self, school_name):
        self.school_name = school_name
        self._db = defaultdict(set)

    def add(self, student, grade):
        self._db[grade].add(student)

    def grade(self, level):
        return self._db.get(level, set())

    def sort(self):
        for grade in self._db:
            self._db[grade] = tuple(student for student in sorted(self._db[grade]))
        return self._db
