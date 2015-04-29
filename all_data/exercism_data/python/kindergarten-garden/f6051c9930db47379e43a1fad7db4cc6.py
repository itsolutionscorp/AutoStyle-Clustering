class Garden(object):
    plant_key = {
        'C': 'Clover',
        'G': 'Grass',
        'R': 'Radishes',
        'V': 'Violets'
    }

    def __init__(self, plant_layout, students=None):
        if students is None:
            self.students = ['Alice', 'Bob', 'Charlie', 'David', 'Eve', 'Fred',
                             'Ginny', 'Harriet', 'Ileana', 'Joseph', 'Kincaid', 'Larry']
        else:
            self.students = sorted(students)

        self.plant_layout = [[i[c:c + 2] for c in range(0, len(i), 2)] for i in plant_layout.split('\n')]

    def plants(self, student):
        sid = self.students.index(student)
        student_plants = [self.plant_layout[r][sid] for r in range(len(self.plant_layout))]

        return [self.plant_key[str(r[c])] for r in student_plants for c in range(len(r))]
