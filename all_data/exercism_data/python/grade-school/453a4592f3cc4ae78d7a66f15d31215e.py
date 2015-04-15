class School:
    def __init__(self, name):
        self.name = name
        self.db = {}

    def add(self, student, grade_):
        if grade_ not in self.db:
            self.db[grade_] = set()
        self.db[grade_].add(student)

    def grade(self, grade_):
        return self.db[grade_] if grade_ in self.db else set([])

    def sort(self):
        sorted_db = {}
        for key in self.db:
            sorted_db[key] = tuple(self.db[key])
        return sorted_db
