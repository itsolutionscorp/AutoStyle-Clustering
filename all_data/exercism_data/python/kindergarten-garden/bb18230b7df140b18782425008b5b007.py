#!/usr/bin/env python


class Garden(object):
    
    STUDENTS = ("Alice", "Bob", "Charlie", 
                "David", "Eve", "Fred", 
                "Ginny", "Harriet", "Ileana", 
                "Joseph", "Kincaid", "Larry")
    LEGEND = {'G': "Grass", 'C': "Clover", 'R': "Radishes", 'V': "Violets"}
    
    def __init__(self, rows, students=STUDENTS):
        self.rows = rows.split()
        self.students = sorted(students)
        
    def plants(self, student):
        i = self.students.index(student) * 2 # accounts for 2 plants each
        result = []
        for row in self.rows:
            result.extend([self.LEGEND[row[i]], self.LEGEND[row[i+1]]])
        return result
    
