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

        # matrix of each students plants, ex: [['VC', 'RR', 'GV', 'RG'], ['RV', 'GC', 'CG', 'CV']]
        self.plant_layout = [[i[c:c + 2] for c in range(0, len(i), 2)] for i in plant_layout.split('\n')]

    def plants(self, student):
        sid = self.students.index(student)

        # list of student plants, each item represents the row, ex: ['VC', 'RV']
        student_plants = [self.plant_layout[r][sid] for r in range(len(self.plant_layout))]

        # develop list by indexing each character of our student plants in the plant_key dict
        return [self.plant_key[str(r[c])] for r in student_plants for c in range(len(r))]
