from itertools import product


class Garden:
    plant_dict = {"V": "Violets",
                  "R": "Radishes",
                  "C": "Clover",
                  "G": "Grass"}

    def __init__(self, garden, students=None):
        if students:
            self._students = students
            self._students = sorted(students)
        else:
            self._students = ["Alice", "Bob", "Charlie", "David",
                              "Eve", "Fred", "Ginny", "Harriet",
                              "Ileana", "Joseph", "Kincaid", "Larry"]
        self._garden = garden.split()

    def plants(self, name):
        index = self._students.index(name) * 2
        return [Garden.plant_dict[self._garden[x][y]]
                for x, y in product(range(2), range(index, index+2))]
