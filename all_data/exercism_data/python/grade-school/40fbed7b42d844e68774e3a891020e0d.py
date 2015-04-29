class School:
    def __init__(self, name):
        self.db = {}

    def add(self, name, grade):
        if self.db.has_key(grade):
            self.db[grade].add(name)
        else:
            self.db[grade] = {name}

    def grade(self, n):
        return self.db.get(n, set())

    def sort(self):
        # Set in python is sorted by default
        return {k: tuple(v) for k, v in self.db.iteritems()}
