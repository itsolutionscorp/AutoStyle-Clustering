__author__ = 'Hinek'

class Garden(object):

    DEFAULT_STUDENTS = ['Alice', 'Bob', 'Charlie', 'David', 'Eve', 'Fred', 'Ginny', 'Harriet', 'Ileana', 'Joseph',
                        'Kincaid', 'Larry']

    def __init__(self, plants, students=DEFAULT_STUDENTS):
        self.students = sorted(students)
        self.PLANT_NAMES = {'C': 'Clover', 'G': 'Grass', 'R': 'Radishes', 'V': 'Violets'}
        self.areas = self.load_areas(plants)

    def plants(self, kid):
        return self.areas[self.students.index(kid)]

    def load_areas(self, plants):
        rows = plants.split('\n')
        row1 = [rows[0][i:i+2] for i in range(0, len(rows[0]), 2)]
        row2 = [rows[1][i:i+2] for i in range(0, len(rows[1]), 2)]
        raw = zip(row1, row2)
        result = []
        for item in raw:
            result.append([self.PLANT_NAMES[item[0][0]],
                          self.PLANT_NAMES[item[0][1]],
                          self.PLANT_NAMES[item[1][0]],
                          self.PLANT_NAMES[item[1][1]]])
        return result
