__author__ = 'Cedric Zhuang'


class Garden(object):
    plants_map = {'C': 'Clover',
                  'G': 'Grass',
                  'R': 'Radishes',
                  'V': 'Violets'}

    def __init__(self, plants, students=None):
        self.all_plants = [list(line) for line in plants.upper().splitlines()]
        if students is not None:
            self.students = sorted(students)
        else:
            self.students = None

    def plants(self, kinder):
        # get the index of the child
        if self.students is not None:
            name_index = self.students.index(kinder) * 2
        else:
            name_index = (bytearray(kinder.upper())[0] - 65) * 2
        results = []
        for plant in self.all_plants:
            for plant_index in plant[name_index:name_index + 2]:
                results.append(Garden.plants_map[plant_index])
        return results
