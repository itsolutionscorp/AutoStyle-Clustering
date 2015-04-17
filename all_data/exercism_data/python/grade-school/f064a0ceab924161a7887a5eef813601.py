from collections import OrderedDict


class School(object):
    def __init__(self, name):
        self._name = name
        self.db = {}

    def add(self, student, grade):
        self.db.setdefault(grade, set()).add(student)

    def grade(self, g):
        return self.db.get(g, set())

    def sort(self):
        return OrderedDict((g, tuple(sorted(self.db[g]))) for g in sorted(self.db))