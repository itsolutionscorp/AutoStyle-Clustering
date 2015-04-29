class Garden():

    def __init__(self, diagram, students=None):
        self.diagram = diagram.split('\n')
        if students:
            self.students = students
            self.students.sort()
        else:
            self.students = ['Alice', 'Bob', 'Charlie',
                             'David', 'Eve', 'Fred',
                             'Ginny', 'Harriet', 'Ileana',
                             'Joseph', 'Kincaid', 'Larry']
        self.plantnames = {'V':'Violets', 'R':'Radishes',
                           'C':'Clover', 'G':'Grass'}

    def plants(self, student):
        snum = self.students.index(student)
        plants = []
        for row in self.diagram:
            for i in range(2):
                plants.append(self.plantnames[row[2*snum+i]])
        return plants
