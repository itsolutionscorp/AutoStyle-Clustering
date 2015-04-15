from collections import defaultdict
from copy import deepcopy


class School(object):
    def __init__(self, name):
        self.name = name
        self._db = defaultdict(set)

    def add(self, name, grade):
        self._db[grade].add(name)

    def sort(self):
        return {grade: tuple(sorted(self._db[grade])) for grade in self._db}

    @property
    def db(self):
        return deepcopy(self._db)

    def grade(self, grade):
        return self.db[grade]
