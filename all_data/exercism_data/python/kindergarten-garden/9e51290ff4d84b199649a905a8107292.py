#!/usr/bin/python
# exercism python 16: Kindergarten Garden

class Garden(object):
    
    def __init__(self, rows, students='Alice Bob Charlie David Eve Fred Ginny Harriet Ileana Joseph Kincaid Larry'.split()):
        self.rows = rows.split('\n')
        self.students = students
        self.students.sort()
        self.plant_dict = dict([('R', 'Radishes'),
                                ('G', 'Grass'),
                                ('V', 'Violets'),
                                ('C', 'Clover')])      
        
    def plants(self, student):
        row_position = self.students.index(student)*2
        cups = []
        for row in self.rows:
            cups.extend([plant for plant in row[row_position:row_position+2]])
        return [self.plant_dict[plant] for plant in cups]
