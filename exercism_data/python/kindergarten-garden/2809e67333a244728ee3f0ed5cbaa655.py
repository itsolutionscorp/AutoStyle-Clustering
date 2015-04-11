
PLANT_NAME = {'G': 'Grass',
              'C': 'Clover',
              'R': 'Radishes',
              'V': 'Violets'}

DEFAULT_CHILDREN = ["Alice", "Bob", "Charlie", "David",
                        "Eve", "Fred", "Ginny", "Harriet",
                        "Ileana", "Joseph", "Kincaid", "Larry"]

class Garden:    
    def __init__(self, diagram, students=DEFAULT_CHILDREN):
        self._diagram = diagram.split()
        self._students = sorted(students)

    def plants(self, child):
        column = 2 * self._students.index(child)
        plants = self._diagram[0][column:column+2] + self._diagram[1][column:column+2]
        return [PLANT_NAME[plant] for plant in plants]
