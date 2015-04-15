from collections import defaultdict

class School(object):

    def __init__(self, name):
        self.db = defaultdict(set)
        self.name = name

    def add(self, name, grade):
        self.db[grade].add(name)

    def grade(self, grade):
        return self.db[grade]

    def sort(self):
        return {g: tuple(sorted(s)) for g, s in self.db.items()}
