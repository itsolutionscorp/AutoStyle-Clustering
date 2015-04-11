# -*- coding: utf-8 -*-


class Garden(object):

    students = ["Alice", "Bob", "Charlie", "David", "Eve", "Fred", "Ginny",
                "Harriet", "Ileana", "Joseph", "Kincaid", "Larry"]

    _plants = {"V" : "Violets", "R" : "Radishes", "C" : "Clover", "G" : "Grass"}

    def __init__(self, *args, **kwargs):
        super(self.__class__, self).__init__()
        self.garden =  args[0]
        self.students =  kwargs.get('students', self.students)
        self.students.sort()
        rows = self.garden.split('\n')
        self.row1=rows[0]
        self.row2=rows[1]

    def plants(self, student):
        """
        i (index) is based on 'Each child gets 4 cups, two on each row'
        """
        i = self.students.index(student)*2
        plants_str = self.row1[i:i+2] + self.row2[i:i+2]
        return self._decode_plants(plants_str)

    def _decode_plants(self, garden_str):
        output_str = []
        for c in garden_str:
            output_str.append(self._plants[c])
        return output_str
