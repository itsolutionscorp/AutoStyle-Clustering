class School:
    def __init__(self, name):
        self.name = name
        self.db = {}

    def add(self, kid, grade):
        self.db.setdefault(grade, set()).add(kid)

    def grade(self, n):
        return self.db.get(n, set())

    def sort(self):
        return {k : tuple(self.db[k]) for k in self.db}
