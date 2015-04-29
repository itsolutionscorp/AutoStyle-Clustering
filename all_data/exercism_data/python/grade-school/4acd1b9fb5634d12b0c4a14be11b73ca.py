__author__ = 'emiller42'


class School:

    def __init__(self, school_name):
        self.school_name = school_name
        self.db = {}

    def add(self, student, grade_level):
        if grade_level not in self.db:
            self.db[grade_level] = set()

        self.db[grade_level].add(student)

    def grade(self, grade_level):
        if grade_level in self.db:
            return self.db[grade_level]
        else:
            return set([])

    def sort(self):
        for grade_level in self.db:
            self.db[grade_level] = tuple(name for name in self.db[grade_level])

        return self.db
