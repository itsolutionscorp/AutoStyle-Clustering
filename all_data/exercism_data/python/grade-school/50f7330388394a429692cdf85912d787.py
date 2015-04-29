class School:
    def __init__(self, name):
        self.name = name
        self.db = {}

    def add(self, name, year):
        try:
            self.db[year].add(name)
        except KeyError:
            self.db[year] = set()
            self.db[year].add(name)

    def grade(self, year):
        return self.db.get(year, set())

    def sort(self):
        return [(key, tuple(sorted(self.db[key]))) for key in self.db.keys()]
