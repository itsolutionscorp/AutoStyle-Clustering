class School:
    def __init__(self, name):
        self.name = name
        self.db = {}
        pass

    def add(self, kid, grade):
        if grade in self.db:
            self.db[grade].add(kid)
        else:
            self.db[grade] = set([kid])

    def grade(self, n):
        return self.db[n] if n in self.db else set()

    def sort(self):
        return {k : tuple(self.db[k]) for k in self.db}


