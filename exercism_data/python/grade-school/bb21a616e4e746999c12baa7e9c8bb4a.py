# -*- coding:utf-8 -*-
from collections import defaultdict

class School(object):
    def __init__(self, *args, **kw):
        self.db = defaultdict(set)

    def add(self, name, grade):
        self.db[grade].add(name)

    def grade(self, grade):
        return self.db.get(grade, set())

    def sort(self):
        return dict(
            (grade, tuple(sorted(names))) 
            for grade, names in sorted(self.db.items())
            )
                                      
