__author__ = 'angelo'
from collections import defaultdict, OrderedDict

class School:

    def __init__(self, name):
        self.name = name
        self.db = defaultdict(set)

    def add(self, student, grade):
        self.db[grade].add(student)

    def grade(self, grade):
        return self.db[grade]

    def sort(self):
        db_copy = OrderedDict()
        for k in sorted(self.db.keys()):
            db_copy[k] = tuple(self.db[k])
        return db_copy
