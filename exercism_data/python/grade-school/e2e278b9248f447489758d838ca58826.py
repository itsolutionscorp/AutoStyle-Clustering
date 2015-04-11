from collections import defaultdict

class School(object):
    def __init__(self, name):
        self.name = name
        self.db = defaultdict(set)

    def add(self, student_name, grade_num):
        self.db[grade_num].add(student_name)

    def grade(self, gradeNum):
        return self.db[gradeNum]

    def sort(self):
        return {gradeNum: tuple(sorted(self.db[gradeNum]))
                for gradeNum in self.db.keys()}
