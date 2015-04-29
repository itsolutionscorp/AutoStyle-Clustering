from collections import defaultdict

class School:

    def __init__(self, schoolname):
        self.schoolname = schoolname
        self.db = defaultdict(set)

    def add(self, name, grade):
        self.db[grade].add(name)

    def grade(self, grade):
        return self.db[grade]

    def sort(self):
        return {grade: tuple(sorted(students)) for grade, students in self.db.items()}
