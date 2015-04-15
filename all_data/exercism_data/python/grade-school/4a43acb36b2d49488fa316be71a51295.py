from collections import defaultdict


class School:
    def __init__(self, name):
        self.name = name
        self.db = defaultdict(set)

    def add(self, student, grade):
        self.db[grade].add(student)

    def grade(self, n):
        return self.db[n]

    def sort(self):
        return {grade: tuple(sorted(students))
                for grade, students in self.db.items()}
