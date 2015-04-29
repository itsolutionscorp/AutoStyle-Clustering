plant = {
    'R': 'Radishes',
    'C': 'Clover',
    'G': 'Grass',
    'V': 'Violets',
}

classroom = [
    'Alice', 'Bob', 'Charlie',
    'David', 'Eve', 'Fred',
    'Ginny', 'Harriet', 'Ileana',
    'Joseph', 'Kincaid', 'Larry'
]


class Garden():
    def __init__(self, plants, students=None):
        self.students = sorted(students or []) or classroom
        self.garden = [[row[i:i + 2] for i in range(0, len(row) - 1, 2)] for row in plants.split()]

    def plants(self, student):
        i = self.students.index(student)
        return [plant[p] for p in [r for r in self.garden[0][i] + self.garden[1][i]]]
