from collections import defaultdict


class School(object):
    def __init__(self, name):
        self.db = defaultdict(set)

    def add(self, name, grade):
        self.db[grade].add(name)

    def grade(self, number):
        return self.db.get(number, set())

    def sort(self):
        return {
            grade: tuple(sorted(names))
            for grade, names in self.db.iteritems()
        }
