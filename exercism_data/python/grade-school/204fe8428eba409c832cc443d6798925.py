from collections import defaultdict
class School(object):
    def __init__(self, school_name):
        self.name = school_name
        self.db = defaultdict(set)

    def add(self, student_name, grade):
        self.db[grade].add(student_name)

    def grade(self, grade):
        return self.db[grade]

    def sort(self):
        return { grade: tuple(sorted(students))
                 for (grade, students) in self.db.items()}
