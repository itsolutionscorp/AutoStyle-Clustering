class School:
    def __init__(self, name):
        self.name = name
        self.db = {}

    def add(self, name, grade):
        if grade not in self.db:
            self.db[grade] = set()
        self.db[grade].add(name)

    def grade(self, grade):
        if grade not in self.db:
            return set()
        return self.db[grade]

    def sort(self):
        return {k: tuple(sorted(self.grade(k))) for k in self.db.keys()}
