class School(object):

    def __init__(self, name):
        self.name = name
        self.db = {}

    def grade(self, grade):
        return self.db.get(grade, set())

    def add(self, student, grade):
        if grade in self.db:
            self.db[grade].add(student)
        else:
            self.db[grade] = {student}

    def sort(self):
        return [(key, tuple(sorted(value))) for key, value in sorted(self.db.items())]
