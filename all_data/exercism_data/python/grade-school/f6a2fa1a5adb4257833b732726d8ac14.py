class School:
    def __init__(self, name):
        self.db = {}

    def add(self, name, grade):
        if grade not in self.db:
            self.db[grade] = set()
        self.db[grade].add(name)

    def grade(self, grade):
        return self.db.get(grade, set())

    def sort(self):
        return {key: tuple(sorted(value)) for (key, value) in self.db.items()}
