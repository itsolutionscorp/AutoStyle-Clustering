class School:
    def __init__(self, name):
        self.name = name
        self.db = dict()

    def add(self, name, grade):
        if grade in self.db:
            self.db[grade].add(name)
        else:
            self.db[grade] = {name}

    def grade(self, grade):
        if grade in self.db:
            return self.db[grade]
        return set()

    def sort(self):
        for grade in sorted(self.db.keys()):
            yield (grade, tuple(sorted(self.db[grade])))
