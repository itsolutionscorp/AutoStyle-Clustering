class School:
    def __init__(self, name):
        self.name = name
        self.db = {}

    def add(self, student, grade):
        self.db.setdefault(grade, set())
        self.db[grade].add(student)

    def grade(self, grade):
        return self.db.get(grade, set())

    def sort(self):
        return {k: tuple(sorted(v)) for k, v in self.db.items()}
