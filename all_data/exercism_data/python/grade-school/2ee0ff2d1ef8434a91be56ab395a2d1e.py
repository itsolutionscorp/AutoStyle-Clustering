class School(object):

    def __init__(self, name):
        self.db = dict()
        self.name = name

    def grade(self, grade):
        if grade in self.db:
            return self.db[grade]
        else:
            self.db[grade] = set()
            return self.db[grade]

    def add(self, student, grade):
        if grade in self.db:
            self.db[grade].add(student)
        else:
            self.db[grade] = {student}

    def sort(self):
        return [(key, tuple(sorted(value))) for key, value in sorted(self.db.items())]
