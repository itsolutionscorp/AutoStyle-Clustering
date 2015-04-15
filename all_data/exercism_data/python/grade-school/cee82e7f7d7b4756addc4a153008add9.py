import collections


class School(object):
    def __init__(self, name):
        self.name = name
        self.db = collections.defaultdict(set)

    def add(self, student, grade):
        if student not in self.db[grade]:
            self.db[grade].add(student)

    def grade(self, grade):
        return self.db[grade]

    def sort(self):
        return [(grade, tuple(sorted(students)))
                for grade, students in sorted(self.db.items())]
