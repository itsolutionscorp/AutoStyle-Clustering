#!/usr/bin/env python3
class School:
    def __init__(self, school_name):
        self.db = {}

    def add(self, name, student_grade):
        if student_grade  not in self.db:
            self.db[student_grade]= {name}
        else:
            self.db[student_grade].add(name)

    def grade(self, student_grade):
        if student_grade not in self.db:
            return set()
        return self.db.get(student_grade)

    def sort(self):
        for i in self.db.keys():
            self.db[i] = tuple(sorted(list(self.db[i])))
        return sorted(self.db.items())
