from collections import defaultdict, OrderedDict

class School(object):
    def __init__(self, name):
        self._name = name
        self._db = defaultdict(set)

    @property
    def db(self):
        return self._db

    def add(self, student_name, grade):
        self.db[grade].add(student_name)

    def sort(self):
        return dict(sorted([(key, tuple(value)) for key, value in self.db.items()]))

    def grade(self, grade_number):
        return self.db[grade_number]
