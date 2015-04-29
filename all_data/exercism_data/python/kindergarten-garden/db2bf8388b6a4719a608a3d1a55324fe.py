_default_students = tuple(sorted(('Alice', 'Bob', 'Charlie',
                                  'David', 'Eve', 'Fred',
                                  'Ginny', 'Harriet', 'Ileana',
                                  'Joseph', 'Kincaid', 'Larry')))

_plant_dict = {'C': 'Clover', 'G': 'Grass', 'R': 'Radishes', 'V': 'Violets'}


def _letter_to_plant(c):
    return _plant_dict[c]


class Garden():
    def __init__(self, plant_rows, students=None):
        self.plant_rows = ''.join(c for c in plant_rows
                                  if c in _plant_dict.keys() or c == '\n')
        try:
            self.students = tuple(sorted(students))
        except TypeError:
            self.students = _default_students

    def plants(self, child):
        plant_index = 2 * self.students.index(child)
        plant_rows = [map(_letter_to_plant,
                          line[plant_index:plant_index + 2])
                      for line in self.plant_rows.split('\n')]
        return list(plant for row in plant_rows for plant in row)
