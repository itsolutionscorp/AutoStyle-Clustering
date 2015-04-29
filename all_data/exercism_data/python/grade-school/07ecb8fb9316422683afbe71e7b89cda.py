from collections import defaultdict

class School:

    def __init__(self, name):
        self.name = name
        self.db = defaultdict(set)

    def add(self, student, grade):
        self.db[grade].add(student)

    def grade(self, grade_number):
        return self.db[grade_number]

    def sort(self):
        register = dict()
        for grade, roster in self.db.items():
            register[grade] = tuple(sorted(roster))
        return register
