from collections import OrderedDict

class School:

    def __init__(self, schoolName):
        self.schoolName = schoolName
        self.db = dict()

    def add(self, name, gr):
        if gr not in self.db:
            self.db[gr]= set()
        self.db[gr].add(name)

    def grade(self, gr):
        if gr not in self.db:
            return set()
        return self.db[gr]

    def sort(self):
        result = list()
        for k in sorted(self.db.keys()):
            result.append((k, tuple(self.db[k])))
        return result
