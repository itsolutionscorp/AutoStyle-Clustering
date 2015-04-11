from collections import defaultdict

# This is nearly impossible without just cheating

class School(object):

    def __init__(self, name):
        self.name = name
        self.db = defaultdict(set)

    def add(self, student, grade):
        if grade in self.db:
            self.db[grade].add(student)
        else:
            self.db[grade] = {student}

    def grade(self, level):
        return self.db[level]

    def sort(self):
        return sorted((
            grade, tuple(sorted(students)))
            for grade, students in self.db.items())
