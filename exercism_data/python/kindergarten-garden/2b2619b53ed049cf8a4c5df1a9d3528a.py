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

        self.plant_layout = []

        for i in plant_layout.split('\n'):
            line = []
            for c in range(0, len(i), 2):
                line.append(i[c:c + 2])
            self.plant_layout.append(line)

    def plants(self, student):
        sid = self.students.index(student)
        student_plants = []

        for r in range(len(self.plant_layout)):
            student_plants.append(self.plant_layout[r][sid])

        return [self.plant_key[r[c]] for r in student_plants for c in range(len(r))]
