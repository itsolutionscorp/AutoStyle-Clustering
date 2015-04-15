from collections import defaultdict

class School:
    def __init__(self, name):
        self.name = name
        self.db = {}
    def add(self, name, grade):
        self.db.setdefault(grade, set()).add(name) 
    def grade(self, grade):
        return self.db.setdefault(grade, set())
    def sort(self):
        return dict((k, tuple(sorted(v))) for k, v in self.db.iteritems())
