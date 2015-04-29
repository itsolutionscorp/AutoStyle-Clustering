PLANTS = {
    'C': 'Clover',
    'G': 'Grass',
    'R': 'Radishes',
    'V': 'Violets',
}


class Garden(object):
    names = [
        'Alice', 'Bob', 'Charlie', 'David',
        'Eve', 'Fred', 'Ginny', 'Harriet',
        'Ileana', 'Joseph', 'Kincaid', 'Larry'
    ]

    def __init__(self, rows, students=False):
        if students:
            students.sort()
            self.names = students

        self.rows = rows.split()
        self.students = {}

        for i in range(0, len(self.rows[0]), 2):
            name = self.names[int(i / 2)]
            self.students[name] = [self.rows[0][i], self.rows[0][i + 1],
                                   self.rows[1][i], self.rows[1][i + 1]]

    def plants(self, name):
        return [PLANTS[plant] for plant in self.students[name]]
