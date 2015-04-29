class Garden(object):
    def __init__(self, plant_layout, students=None):
        if students is None:
            self.students = ['Alice', 'Bob', 'Charlie', 'David', 'Eve', 'Fred',
                             'Ginny', 'Harriet', 'Ileana', 'Joseph', 'Kincaid', 'Larry']
        else:
            self.students = sorted(students)

        self.plant_key = {
            'C': 'Clover',
            'G': 'Grass',
            'R': 'Radishes',
            'V': 'Violets'
        }

        self.plant_layout = []

        for i in plant_layout.split('\n'):
            line = []
            for c in range(0, len(i), 2):
                line.append(i[c:c + 2])
            self.plant_layout.append(line)

    def plants(self, student):
        student_plants = []
        plant_list = []

        for n in range(len(self.students)):
            if self.students[n] == student:
                for r in range(len(self.plant_layout)):
                    student_plants.append(self.plant_layout[r][n])

        for r in student_plants:
            for c in range(len(r)):
                plant_list.append(self.plant_key[r[c]])

        return plant_list
