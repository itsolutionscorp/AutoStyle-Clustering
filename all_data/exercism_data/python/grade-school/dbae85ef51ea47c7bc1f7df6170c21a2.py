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
        return dict(((g, tuple(sorted(students)))
                for g, students in self.db.items()))
