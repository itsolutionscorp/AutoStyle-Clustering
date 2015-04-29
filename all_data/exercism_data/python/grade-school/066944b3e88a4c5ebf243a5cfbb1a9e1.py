#!/usr/bin/env python

class School:
    def __init__(self, name):
        self.name = name
        self.__db = {}
    
    @property
    def db(self):
        return {grade: students.copy() for grade, students in self.__db.items()}
    
    def add(self, name, grade):
        if not grade in self.__db.keys():
            self.__db[grade] = set()
        self.__db[grade].add(name)
    
    def grade(self, g):
        if g in self.__db.keys():
            return self.__db[g].copy()
        else:
            return set()
    
    def sort(self):
        return {grade: tuple(sorted(students)) for grade, students in self.__db.items()}
