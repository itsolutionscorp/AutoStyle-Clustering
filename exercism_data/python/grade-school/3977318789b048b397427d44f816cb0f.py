class School():
    def __init__(self, name="school"):
        self.name = name
        self.db = {}

    def add(self, student, year):
        if year in self.db:
            self.db[year].add(student)
        else:
            self.db[year] = {student}

    def grade(self, year):
        if year not in self.db:
            return set()
        else:
            return self.db[year]

    def sort(self):
        return {d:tuple(y for y in self.db[d]) for d in self.db}
