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
        result = list()
        for k in sorted(self.db.keys()):
            result.append((k, tuple(self.db[k])))
        return result
