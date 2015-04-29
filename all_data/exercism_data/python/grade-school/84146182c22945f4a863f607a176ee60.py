from collections import defaultdict

__author__ = 'jimblackler'


class School(object):
    def __init__(self, name):
        self.db = defaultdict(set)

    def add(self, student, grade):
        self.db[grade].add(student)

    def grade(self, grade):
        return self.db[grade]

    def sort(self):
        return {a: tuple(b) for a, b in sorted(self.db.items())}
