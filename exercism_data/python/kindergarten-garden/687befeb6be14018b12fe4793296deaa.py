class Garden:
    STUDENTS = ['Alice', 'Bob', 'Charlie', 'David',
                'Eve', 'Fred', 'Ginny', 'Harriet',
                'Ileana', 'Joseph', 'Kincaid', 'Larry']

    PLANTS = {
        'R': 'Radishes',
        'C': 'Clover',
        'G': 'Grass',
        'V': 'Violets'
    }

    def __init__(self, rows, students=None):
        self.rows = rows.split('\n')
        self.students = students or self.STUDENTS
        self.students.sort()

    def plants(self, name):
        i = self.students.index(name)
        plants = self.__all_plants__()
        return sum([row[0 + 2 * i:2 + 2 * i] for row in plants], [])

    def __all_plants__(self):
        return [[self.PLANTS[plant] for plant in row] for row in self.rows]
