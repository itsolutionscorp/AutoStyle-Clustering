"""
garden.py: a program that, given a diagram, can tell you which plants
each child in the kindergarten class is responsible for.
"""


class Garden(object):
    """
    A garden object.
    Attributes:
        diagram: a string containing plant abbreviations with rows separted by
        new lines (\n)
        students: an optional string containing space-seperated student names
    """
    # the dictionary of plant names and abbreviations
    __plant_names = {
        'C': 'Clover',
        'G': 'Grass',
        'R': 'Radishes',
        'V': 'Violets'
    }

    def __init__(self, diagram, students=("Alice Bob Charlie David Eve Fred "
                                          "Ginny Harriet Ileana Joseph "
                                          "Kincaid Larry").split()):
        self.plant_rows = diagram.split()
        self.students = sorted(students)

    def plants(self, student):
        """
        Method to return a list of plants for a given student.
        Arguments:
            student: the name of the student as a string
        """
        row_start = self.students.index(student) * 2
        row = slice(row_start, row_start + 2)
        return [
            self.__plant_names[abbr] for abbr in (
                self.plant_rows[0][row] + self.plant_rows[1][row]
            )
        ]
