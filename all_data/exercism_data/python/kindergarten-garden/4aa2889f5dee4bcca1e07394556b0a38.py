#!/usr/bin/env python3
class Garden:
    def __init__(self, garden_looks, students=None):
        self.garden_looks = garden_looks
        if not students:
            self.students = ('Alice', 'Bob', 'Charlie', 'David',
                         'Eve', 'Fred', 'Ginny', 'Harriet',
                         'Ileana', 'Joseph', 'Kincaid',  'Larry')
        else:
            self.students = sorted(students)
        self.seeds = {'G': 'Grass', 'C': 'Clover', 'R': 'Radishes', 'V': 'Violets'}

    def plants(self, name):
        plants_name = []
        diagram = self.garden_looks.split()
        plants_loc = (self.students.index(name) + 1) * 2
        for i in diagram:
            plants_name.append(self.seeds[i[plants_loc-2:plants_loc][0]])
            plants_name.append(self.seeds[i[plants_loc-2:plants_loc][1]])
        return plants_name
