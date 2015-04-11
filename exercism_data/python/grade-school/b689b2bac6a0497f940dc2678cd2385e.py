# -*- coding: utf-8 -*-
from collections import Sequence

class School(object):
    def __init__(self, school_name):
        self.school_name = school_name
        self.db = {}

    def add(self, student_name, student_grade):
        if student_grade not in self.db:
            self.db[student_grade] = {student_name}
        else:
            self.db[student_grade].add(student_name)

    def grade(self, student_grade):
        if student_grade not in self.db:
            self.db[student_grade] = set()
        return self.db[student_grade]

    def sort(self):
        db = self.db
        students_list = []
        index_list = sorted(list(db.keys()))
        for x in index_list:
            student_tuple = (x, tuple(sorted([y for y in db[x]])))
            students_list.append(student_tuple)
        return students_list
