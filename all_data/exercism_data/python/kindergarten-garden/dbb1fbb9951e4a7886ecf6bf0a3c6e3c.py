class Garden(object):
    PLANTS = {'V': 'Violets', 'R': 'Radishes', 'C': 'Clover', 'G': 'Grass'}

    def __init__(
        self,
        garden,
            students="Alice Bob Charlie David Eve Fred Ginny Harriet Ileana Joseph Kincaid Larry".split()):
        self.garden = garden
        self.students = students
        self.students.sort()

    def plants(self, student):
        student_position = self.students.index(student)
        rows = self.garden.split('\n')
        student_plants = []
        for row in rows:
            for plant in range(student_position * 2, student_position * 2 + 2):
                student_plants.append(self.PLANTS[row[plant]])
        return student_plants
