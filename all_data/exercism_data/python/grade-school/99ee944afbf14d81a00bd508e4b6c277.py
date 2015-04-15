class School:
    def __init__(self, name):
        self.name = name
        self.db = {}
        
    def add(self, fname, grade):
        self.db[grade] = self.db.get(grade, set())
        self.db[grade].add(fname)

    def grade(self, grade):
        return self.db.get(grade, set())

    def sort(self):
        return {g: tuple(sorted(self.db[g])) for g in self.db}
