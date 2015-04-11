from textwrap import wrap
from functools import partial


class Garden(object):

    plant_names = {
        'V': 'Violets',
        'R': 'Radishes',
        'C': 'Clover',
        'G': 'Grass'
    }

    def __init__(self, garden, students=None):
        self.garden = [''.join(x) for x in zip(*map(partial(wrap, width=2), garden.split('\n')))]
        if students:
            self.students = sorted(students)
        else:
            self.students = ['Alice', 'Bob', 'Charlie', 'David',
                             'Eve', 'Fred', 'Ginny', 'Harriet',
                             'Ileana', 'Joseph', 'Kincaid', 'Larry']

    def plants(self, name):
        if name not in self.students:
            raise ValueError
        pots = self.garden[self.students.index(name)]
        return [self.plant_names.get(x) for x in pots]
