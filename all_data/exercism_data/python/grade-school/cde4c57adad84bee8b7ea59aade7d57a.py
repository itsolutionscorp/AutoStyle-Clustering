from collections import OrderedDict

class School:
    def __init__(self, name):
        self.name = name
        self.db = {}

    def add(self, student_name, grade):
        if self.db.get(grade):
            self.db[grade].add(student_name)
        else:
            self.db[grade] = set([student_name])

    def grade(self, grade):
        return self.db.get(grade, set())

    def sort(self):
        d = {}

        for k, v in self.db.items():
            d[k] = tuple(sorted(v))

        return d
