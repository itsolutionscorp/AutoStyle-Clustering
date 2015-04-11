class Garden(object):

    plants_dict = {'V': 'Violets',
                   'R': 'Radishes',
                   'C': 'Clover',
                   'G': 'Grass'}

    def __init__(self, garden, students=None):
        self.garden = garden.split('\n')
        if students:
            self.students = sorted(students)
        else:
            self.students = ['Alice',
                             'Bob',
                             'Charlie',
                             'David',
                             'Eve',
                             'Fred',
                             'Ginny',
                             'Harriet',
                             'Ileana',
                             'Joseph',
                             'Kincaid',
                             'Larry']

    def plants(self, student):
        student_index = self.students.index(student) * 2
        result = []
        result += (self.garden[0][student_index:student_index+2] +
                   self.garden[1][student_index:student_index+2])
        return [self.plants_dict[x] for x in result]
