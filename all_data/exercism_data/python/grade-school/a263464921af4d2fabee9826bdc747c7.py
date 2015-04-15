import collections

class School:

    def __init__(self, name):
        self.name = name
        self.db = collections.defaultdict(set)

    def add(self, student, grade):
        self.db[grade].add(student)

    def grade(self, grade):
        return self.db[grade]

    def sort(self):
        return dict([(x,tuple(y)) for (x,y) in sorted(self.db.items(), key=lambda key_val: key_val[0])])
