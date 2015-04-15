import string

PLANT_LOOKUP = {'G': 'Grass',
                'R': 'Radishes',
                'V': 'Violets',
                'C': 'Clover'}


class Garden(object):

    def __init__(self, planters, students=None):
        self.planters = planters.split()
        if students:
            self.students = sorted(students)
        else:
            self.students = None

    def plants(self, student):
        student_plants = []
        if self.students:
            index = self.students.index(student)
        else:
            index = string.ascii_uppercase.index(student[0])
        for row in self.planters:
            student_plants.append(PLANT_LOOKUP[row[index * 2]])
            student_plants.append(PLANT_LOOKUP[row[index * 2 + 1]])

        return student_plants
