# -*- coding: utf-8 -*-
"""
Created on Mon Sep 29 20:22:28 2014

@author: johann
"""

class School():
    def __init__(self,name):
        self.name = name
        self.db = {}
    def add(self,name,grade):
        if grade not in self.db:
            self.db[grade] = {name}
        else:
            tmp = self.db.get(grade)
            tmp.add(name)
            self.db[grade] = tmp
    def grade(self,grade):
        if self.db.get(grade) == None:
            return set()
        return self.db.get(grade)
    def sort(self):
        tmp
        for self.db.keys in self.db:
            
   
