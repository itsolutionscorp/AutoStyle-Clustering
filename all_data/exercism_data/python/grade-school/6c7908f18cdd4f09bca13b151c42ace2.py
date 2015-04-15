import collections

class School(object):
    def __init__(self, name):
        self.db = collections.defaultdict(set)

    def add(self, name, grade):
        self.db[grade].add(name)

    def grade(self, n):
        return self.db[n]

    def sort(self):
        return [(k, tuple(sorted(v))) for k, v in sorted(self.db.items())]
