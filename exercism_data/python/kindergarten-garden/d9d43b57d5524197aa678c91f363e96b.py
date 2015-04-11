__author__ = 'Cedric Zhuang'


class Garden(object):
    plants_map = {'C': 'Clover',
                  'G': 'Grass',
                  'R': 'Radishes',
                  'V': 'Violets'}

    children = """
            Alice Bob Charlie David
            Eve Fred Ginny Harriet
            Ileana Joseph Kincaid Larry
            """

    def __init__(self, plants, students=children.split()):
        self.all = [list(line) for line in plants.upper().splitlines()]
        self.students = sorted(students)

    def plants(self, kinder):
        name = self.students.index(kinder) * 2
        plant_index = self.all[0][name:name + 2] + self.all[1][name:name + 2]
        return [Garden.plants_map[i] for i in plant_index]
