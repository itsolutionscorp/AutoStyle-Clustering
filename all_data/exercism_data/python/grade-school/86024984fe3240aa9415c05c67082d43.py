from collections import OrderedDict

class School:
    def __init__(self, school_name):
        self.school_name = school_name
        self.db = dict()

    def add(self, student, grade_number):
        if grade_number not in self.db:
            self.db[grade_number] = set()
        self.db[grade_number].add(student)

    def grade(self,grade_number):
        return set() if grade_number not in self.db else self.db[grade_number]

    def sort(self):
        sorted_grades = OrderedDict()
        for grade in self.db:
            sorted_grades[grade] = tuple([student for student in self.db[grade]])
        return sorted_grades
