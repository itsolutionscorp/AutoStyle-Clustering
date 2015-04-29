__author__ = 'agupt15'


class School:
    def __init__(self, name):
        self._name = name
        self.db = {}

    def add(self, student, grade):
        if grade not in self.db:
            self.db[grade] = set()

        self.db[grade].add(student)

    def grade(self, grd):
        if grd in self.db:
            return self.db[grd]
        return set()

    def sort(self):
        sorted_db = sorted(self.db.items(), key=lambda x: x)
        result = []
        for grade, students in sorted_db:
            result.append((grade, tuple(students)))
        return result
