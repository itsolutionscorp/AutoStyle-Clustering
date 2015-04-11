__author__ = 'Hinek'

class School(object):

    def __init__(self, name):
        self.name = name
        self.db = {}

    def add(self, student, grade):
        if grade in self.db:
            self.db[grade].add(student)
        else:
            self.db[grade] = {student}

    def grade(self, grade):
        if grade in self.db:
            return self.db[grade]
        else:
            return set()

    def sort(self):
        result = []
        for key in sorted(self.db.keys()):
            result.append( (key, tuple(self.db[key])) )
        return result
