class School:
    def __init__(self, name):
        self.name = name
        self.db = {}

    def add(self, name, grade):
        try:
            self.db[grade].add(name)
        except KeyError:
            self.db[grade] = {name}

    def grade(self, grade):
        try:
            return self.db[grade]
        except KeyError:
            return set()

    def sort(self):
        return sorted((g, tuple(sorted(s)))
                      for g, s in self.db.items())
