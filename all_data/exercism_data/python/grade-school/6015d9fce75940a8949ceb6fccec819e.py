#!/usr/bin/env python

class School(object):
    
    def __init__(self, name):
        self.name = name
        self.db = {}
        
    def add(self, student, grade):
        try:
            self.db[grade].add(student)
        except KeyError:
            self.db[grade] = set([student])
        
    def grade(self, grade):
        return self.db.get(grade, set())
        
    def sort(self):
        return {k: tuple(sorted(v)) for k, v in self.db.iteritems()}
