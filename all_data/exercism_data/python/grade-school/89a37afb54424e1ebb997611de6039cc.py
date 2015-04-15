from collections import defaultdict


class School(object):

    def __init__(self, name):
        self.name = name
        self._db = defaultdict(set)

    def add(self, name, grade):
        self._db[grade].add(name)

    @property
    def db(self):
        return self._db

    def grade(self, n):
        return self._db[n]
