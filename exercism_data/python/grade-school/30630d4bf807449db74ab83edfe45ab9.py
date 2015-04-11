from collections import defaultdict

class School:
    def __init__(self, name):
        self.name = name
        self.db = defaultdict(set)

    def add(self, student, grade):
        self.db[grade].add(student)

    def grade(self, grade):
        return self.db[grade]

    def sort(self):
        return {k: tuple(sorted(v)) for k, v in self.db.items()}
