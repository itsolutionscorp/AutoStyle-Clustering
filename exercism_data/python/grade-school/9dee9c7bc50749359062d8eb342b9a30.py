from collections import OrderedDict

class School:
    def __init__(self, name: str):
        self.db = OrderedDict()

    def add(self, student, grade):
        if grade in self.db:
            self.db[grade].add(student)
        else:
            self.db[grade] = {student}

    def grade(self, grade):
        return self.db[grade] if grade in self.db else set()

    def sort(self):
        return [(grade, tuple(students)) for grade, students in self.db.items()]
