class School(object):
    def __init__(self, name):
        self.name = name
        self.db = {}

    def add(self, student, grade):
        if not grade in self.db:
            self.db[grade] = set()

        self.db[grade].add(student)

    def grade(self, grade):
        if not grade in self.db:
            return set()

        return self.db[grade]

    def sort(self):
        return [(grade, tuple(student for student in sorted(self.db[grade]))) for grade in sorted(self.db)]
