from itertools import product

class Garden:
    DEFAULT_STUDENTS = [
            'Alice', 'Bob', 'Charlie', 'David',
            'Eve', 'Fred', 'Ginny', 'Harriet',
            'Ileana', 'Joseph', 'Kincaid','Larry'
            ]
    PLANT_FULLNAMES = {
            'G': 'Grass',
            'C': 'Clover',
            'R': 'Radishes',
            'V': 'Violets'
            }
    def __init__(self, plants, students = DEFAULT_STUDENTS):
        self.students = {name: index for index, name in enumerate(sorted(students))}
        self.garden = [[plant for plant in row] for row in plants.split("\n")]

    def plants(self, student_name):
        student_index = self.students[student_name]
        indexes = product([0, 1], [2 * student_index, 2 * student_index + 1])
        plants = [self.garden[i][j] for i, j in indexes]
        return [self.PLANT_FULLNAMES[p] for p in plants]
