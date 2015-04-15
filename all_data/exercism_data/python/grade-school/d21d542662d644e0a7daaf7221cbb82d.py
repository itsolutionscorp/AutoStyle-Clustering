#!/usr/bin/python
class School():
    def __init__(self, name):
        self.name = name
        self.db = {}

    def add(self, student, grade):
        students = self.db.get(grade, set())
        students.add(student)
        self.db[grade] = students

    def grade(self, grade):
        return self.db.get(grade, set())

    def sort(self):
        result = {}
        for grade in self.db.keys():
            result[grade] = tuple(sorted(list(self.db[grade])))
        return result
    
