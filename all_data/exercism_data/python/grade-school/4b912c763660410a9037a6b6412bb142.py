from collections import OrderedDict

class School:

    def __init__(self, schoolName):
        self.schoolName = schoolName
        self.db = dict()

    def add(self, name, gr):
        self.db.setdefault(gr, set()).add(name)

    def grade(self, gr):
        return self.db.get(gr, set())

    def sort(self):
        return [(k, tuple(sorted(self.db[k]))) for k in sorted(self.db.keys())]
