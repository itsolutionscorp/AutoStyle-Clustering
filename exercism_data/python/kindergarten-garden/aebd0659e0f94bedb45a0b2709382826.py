STUDENTS = ('Alice', 'Bob', 'Charlie', 'David', 'Eve',
            'Fred', 'Ginny', 'Harriet', 'Ileana', 'Joseph',
            'Kincaid', 'Larry')

plant_to_name = {'V' : 'Violets', 'R' : 'Radishes',
                 'G' : 'Grass', 'C':'Clover'}

NUM_PLANTS = 2

class Garden:
    def __init__(self, garden, students=STUDENTS):
        self.garden = garden.split('\n')
        self.students = sorted(students)

    def student_index(self, student):
        """
        Returns the position that a student's garden
        starts. By default, each student gets 2 plants
        per row.
        """
        return NUM_PLANTS * self.students.index(student)

    def plants(self, student):
        index = self.student_index(student)
        columns = zip(*self.garden)[index: index + NUM_PLANTS]

        plants = []
        for row in zip(*columns):
            for plant in row:
                plants.append(plant)

        return [plant_to_name[plant] for plant in plants]
