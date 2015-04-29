from collections import defaultdict, OrderedDict

class School:
    def __init__(self, name=''):
        self.name = name
        self.db = defaultdict(set)

    def add(self, name, grade):
        self.db[grade].add(name)

    def grade(self, grade):
        return self.db[grade]

    def sort(self):
        result = dict(self.db)
        result.update((key, tuple(val)) for key, val in result.items())
        return OrderedDict(result.items())
