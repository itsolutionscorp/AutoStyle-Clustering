from collections import OrderedDict


class School(object):

    def __init__(self, name, db=None):
        self.name = name
        if db:
            self.db = db
        else:
            self.db = OrderedDict()

    def add(self, student_name, grade):
        self.db[grade] = self.db.get(grade, set()).union(set((student_name, )))

    def grade(self, grade):
        return self.db.get(grade, set())

    def sort(self):
        return OrderedDict({key: tuple(self.db[key]) for key in sorted(self.db)})
