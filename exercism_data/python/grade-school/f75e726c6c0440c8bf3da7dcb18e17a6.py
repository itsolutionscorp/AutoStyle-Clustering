import collections


class School(object):
    def __init__(self, _):
        self.db = collections.defaultdict(set)

    def add(self, student_name, grade):
        self.db[grade].add(student_name)

    def grade(self, grade):
        return self.db[grade]

    def sort(self):
        return {grade: tuple(sorted(student_names))
                for grade, student_names in self.db.items()}
