from operator import itemgetter

class School:

    def __init__(self, name):
        self.name = name
        self.db = {}

    def add(self, student_name, grade):
        if grade in self.db:
            self.db[grade].add(student_name)
        else:
            self.db[grade] = {student_name}

    def grade(self, grade):
        if grade in self.db:
            return self.db[grade]
        else:
            return set()

    def sort(self):
        sorted_keys_db = sorted(self.db.items())
        sorted_db = []
        for grade, students in sorted_keys_db:
            sorted_db.append((grade, tuple(sorted(students))))
        return sorted_db
