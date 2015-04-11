__author__ = 'jimblackler'


def to_plant_name(code):
    return {'G': 'Grass', 'C': 'Clover', 'R': 'Radishes', 'V': 'Violets'}[code]


class Garden(object):
    def __init__(self, rows_string, students=None):
        self.rows = rows_string.splitlines()
        if students is None:
            students = 'Alice Bob Charlie David Eve Fred Ginny Harriet Ileana Joseph Kincaid Larry'.split(' ')
        self.students = sorted(students)

    def plants(self, name):
        name_index = self.students.index(name)
        return [to_plant_name(self.rows[0][name_index * 2]),
                to_plant_name(self.rows[0][name_index * 2 + 1]),
                to_plant_name(self.rows[1][name_index * 2]),
                to_plant_name(self.rows[1][name_index * 2 + 1])]
