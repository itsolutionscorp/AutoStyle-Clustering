class Garden:
    def __init__(self, pots, students=[]):
        self.pots = pots.split()
        if not students:
            self.students = ['Alice', 'Bob', 'Charlie', 'David',
                             'Eve', 'Fred', 'Ginny', 'Harriet', 'Ileana',
                             'Joseph', 'Kincaid', 'Larry']
        else:
            self.students = sorted(students)

        self.names = {'G': 'Grass', 'C': 'Clover', 'R': 'Radishes', 'V': 'Violets'}

    def plants(self, student):
        i = self.students.index(student) * 2
        plants =[self.pots[0][i], self.pots[0][i+1],
                 self.pots[1][i], self.pots[1][i+1]]

        return [self.names[plant] for plant in plants]
