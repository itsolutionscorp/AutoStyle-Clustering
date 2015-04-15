from collections import defaultdict

class School(object):

    def __init__(self, name=None):
        self.name = name
        self.db = defaultdict(set)

    def add(self, student, ngrade):
        self.db[ngrade].add(student)

    def grade(self, ngrade):
        return self.db[ngrade]

    def sort(self):
        return {ngrade: tuple(sorted(students)) for ngrade, students in self.db.iteritems()}
