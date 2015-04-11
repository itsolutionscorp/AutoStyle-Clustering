from collections import OrderedDict, defaultdict
class School():
    def __init__(self, name):
        self.name = name
        self.db = defaultdict(lambda: set())

    def add(self, name, grade):
        self.db[grade].add(name)

    def grade(self, grade):
        return self.db[grade]

    def sort(self):
        for students in self.db.items()[1]:
            students.sort()
        return dict(sorted(self.db.items(), key=lambda x: x[0]))
