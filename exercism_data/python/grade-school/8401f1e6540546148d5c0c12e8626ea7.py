class School(object):
    def __init__(self, name):
        self.name = name
        self.db = {}

    def add(self, student, grade_num):
        if grade_num not in self.db:
            self.db[grade_num] = set()
        self.db[grade_num].add(student)

    def grade(self, grade_num):
        return self.db.get(grade_num, set())

    def sort(self):
        return ((grade, tuple(sorted(self.db[grade])))
                for grade in sorted(self.db.keys()))
