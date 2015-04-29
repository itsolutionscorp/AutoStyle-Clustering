import collections


class School(object):
    def __init__(self, school_name):
        self.school_name = school_name
        self.db = collections.defaultdict(set)

    def add(self, student, grade_number):
        self.db[grade_number].add(student)

    def grade(self, grade_number):
        return self.db.get(grade_number, set())

    def sort(self):
        return {k: tuple(sorted(v)) for k, v in sorted(self.db.items())}
