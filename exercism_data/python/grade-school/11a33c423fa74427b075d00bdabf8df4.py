class School:
    def __init__(self, name):
        self.name = name
        self.db = {}

    def add(self, student, grade):
        if grade in self.db:
            self.db[grade].add(student)
        else:
            self.db[grade] = set([student])

    def grade(self, n):
        if n in self.db:
            return self.db[n]
        else:
            return set()

    def sort(self):
        keys = sorted(self.db)
        result = {}
        for key in keys:
            result[key] = tuple(self.db[key])
        return result
