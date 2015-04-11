import collections


class School(object):

    def __init__(self, school_name):
        self.school_name = school_name
        self.db = collections.defaultdict(set)

    def __str__(self):
        return self.school_name

    def add(self, student, grade):
        self.db[grade].add(student)

    def grade(self, grade_number):
        return self.db[grade_number]

    def sort(self):
        grades = sorted([key for key in self.db])
        sorted_list = [(grade, tuple(sorted([name for name in self.db[grade]]))) for grade in grades]
        return sorted_list
