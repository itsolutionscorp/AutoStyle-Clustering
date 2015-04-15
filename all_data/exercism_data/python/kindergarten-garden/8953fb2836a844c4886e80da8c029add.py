class Garden(object):
    KEY = {
        'C': 'Clover',
        'G': 'Grass',
        'R': 'Radishes',
        'V': 'Violets'
    }

    def __init__(self, garden, students=None):
        self.garden = [list(row) for row in garden.split()]
        if students is not None:
            students.sort()
        else:
            students = ('Alice', 'Bob', 'Charlie', 'David', 'Eve', 'Fred',
                        'Ginny', 'Harriet', 'Ileana', 'Joseph', 'Kincaid',
                        'Larry')
        self.students = students

    def plants(self, student):
        idx = self.students.index(student) * 2
        plot = self.garden[0][idx:idx + 2] + self.garden[1][idx:idx + 2]
        return [self.KEY[p] for p in plot]
