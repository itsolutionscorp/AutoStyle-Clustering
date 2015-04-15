class Garden:
    DEFAULT_STUDENTS = ['Alice', 'Bob', 'Charlie', 'David', 'Eve', 'Fred',
            'Ginny', 'Harriet', 'Ileana', 'Joseph', 'Kincaid', 'Larry']

    def __init__(self, plots, students=None):
        self.plots = plots.split()
        students = students or self.DEFAULT_STUDENTS
        self.students = sorted(students)

    def plants(self, student):
        i = self.students.index(student) * 2
        plants =[self.plots[0][i:i+2] + self.plots[1][i:i+2]]

        return [self.plant_name(plant)
                for plant in plants.pop()]

    def plant_name(self, abbr):
        return {'G': 'Grass',
                'C': 'Clover',
                'R': 'Radishes',
                'V': 'Violets'}[abbr]
