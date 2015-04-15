# -*- coding: utf-8 -*-
"""
Created on Fri Nov 07 12:01:58 2014

@author: Blair
"""

class Garden:
    
    
    def __init__(self, plantString, students=None):
        self.plantDict = {"G":"Grass", "C":"Clover", "R":"Radishes", "V":"Violets"}
        self.plantsRow1 = list(plantString[:plantString.index("\n")])
        print(self.plantsRow1)
        self.plantsRow2 = list(plantString[plantString.index("\n")+1:])
        print(self.plantsRow2)
        if students == None:
            self.students = ["Alice", "Bob", "Charlie", "David", "Eve", "Fred", "Ginny", "Harriet", "Ileana", "Joseph", "Kincaid", "Larry"]
        else:
            self.students = sorted(students);
        print(self.students)
       
       
    def plants(self, studentName):
        plantList = []
        i = self.students.index(studentName)
        i = i + i
        plantList.append(self.plantDict[self.plantsRow1[i]])
        plantList.append(self.plantDict[self.plantsRow1[i+1]])
        plantList.append(self.plantDict[self.plantsRow2[i]])
        plantList.append(self.plantDict[self.plantsRow2[i+1]])
        return plantList
        
               
