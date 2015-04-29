class School():
    def __init__(self, name):
        self.name = name

        self.db = {}

    def add(self, student, grade):
        if not grade in self.db:
            self.db[grade] = {student}
        else:
            self.db[grade].add(student)

    def grade(self, n):
        return set() if not n in self.db else self.db[n]

    def sort(self):
        return dict((i, tuple(self.grade(i))) for i in self.db)
