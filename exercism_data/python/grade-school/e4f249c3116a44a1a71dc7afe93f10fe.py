from collections import OrderedDict


class School:

    def __init__(self, name):
        self.name = name
        self.db = {}

    def add(self, name, grade):
        if grade in self.db:
            self.db[grade].add(name)
        else:
            self.db[grade] = {name}

    def grade(self, grade):
        return self.db.get(grade, set())

    def sort(self):
        return sorted((key, tuple(sorted(value))) for key, value in self.db.items())
