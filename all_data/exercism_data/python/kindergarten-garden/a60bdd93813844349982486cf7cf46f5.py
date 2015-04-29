class Garden:

    default_students = ['Alice', 'Bob', 'Charlie', 'David', 'Eve', 'Fred', 'Ginny', 'Harriet', 'Ileana', 'Joseph', 'Kincaid', 'Larry']

    plant_names = { 'G': 'Grass', 'C': 'Clover', 'R': 'Radishes', 'V': 'Violets' }

    def __init__(self, cups, students=None):

        if students is None:
            self.students = list(self.default_students)
        else:
            self.students = sorted(students)

        self.cups = cups.split('\n')


    def plants(self, student):
        i = self.students.index(student)

        plants = [self.cups[0][2 * i], self.cups[0][2 * i + 1], self.cups[1][2 * i], self.cups[1][2 * i + 1]]

        return [self.plant_names[plant] for plant in plants]
