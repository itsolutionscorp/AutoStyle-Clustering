import collections

class School:
    def __init__(self, name):
        self.db = collections.defaultdict(set)

    def add(self, student, grade):
        self.db[grade].add(student)

    def grade(self, grade):
        return self.db[grade]

    def sort(self):
        return collections.OrderedDict(
            sorted([ (k, tuple(sorted(v)))
                     for k, v in self.db.items() ])
        )

    pass
