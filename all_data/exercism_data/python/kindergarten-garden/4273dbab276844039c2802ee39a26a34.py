plant = {
    'R': 'Radishes',
    'C': 'Clover',
    'G': 'Grass',
    'V': 'Violets',
}


class Garden():
    students = [
        'Alice', 'Bob', 'Charlie',
        'David', 'Eve', 'Fred',
        'Ginny', 'Harriet', 'Ileana',
        'Joseph', 'Kincaid', 'Larry'
    ]

    def __init__(self, plants, students=None):
        self.students = sorted(students or []) or self.students
        self.garden = []
        for r in (z for z in str.split(plants, "\n")):
            self.garden.append([[plant[r[i]], plant[r[i + 1]]] for i in range(0, len(r), 2)])

    def plants(self, student):
        i = self.students.index(student)
        return [p for p in [r for r in self.garden[0][i] + self.garden[1][i]]]
