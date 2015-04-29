from collections import defaultdict

class School:
    def __init__(self, name):
        self.name = name
        self.db = defaultdict(set)

    def add(self, name, grade):
        self.db[grade].add(name)

    def grade(self, grade):
        return self.db[grade]

    def sort(self):
        return {key: tuple(sorted(self.grade(key))) for key in self.db.keys()}
