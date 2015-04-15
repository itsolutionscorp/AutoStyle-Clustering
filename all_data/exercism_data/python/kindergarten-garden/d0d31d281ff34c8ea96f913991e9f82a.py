# -*- coding: utf-8 -*-
"""
Created on Sat Sep 27 22:32:38 2014

@author: johann
"""

class Garden():
    
    def __init__(self,plants,students= "Alice Bob Charlie David Eve Fred Ginny Harriet Ileana Joseph Kincaid Larry".split()):
        self.rows = plants.splitlines()
        self.students = students
        self.students.sort()
        
        
    def plants(self,name):
        dict = {"R" : "Radishes", "C" : "Clover" , "G": "Grass", "V":"Violets" }
        index = self.students.index(name)
        plants = [None] * 4
        
        plants[0] = dict[self.rows[0][index * 2]]
        plants[1] = dict[self.rows[0][index * 2 + 1]]
        plants[2] = dict[self.rows[1][index * 2]]
        plants[3] = dict[self.rows[1][index * 2 + 1]]
        return plants
