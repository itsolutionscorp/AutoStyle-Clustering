class School():
    def __init__(self, name):
        self.db = {}
        pass


    def add(self, name, grade):
        if not grade in self.db.keys():
            self.db[grade] = set([])
        self.db[grade].add(name)

    def grade(self, n):
        return self.db[n] if n in self.db.keys() else set([])

    def sort(self):
        return [(x, tuple(y)) for x, y in sorted(self.db.items())]
