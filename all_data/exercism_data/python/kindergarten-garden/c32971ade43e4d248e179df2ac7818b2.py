class Garden(object):
    def __init__(self, cups, students=['Alice', 'Bob', 'Charlie', 'David', 'Eve', 'Fred', 'Ginny', 'Harriet', 'Ileana', 'Joseph','Kincaid', 'Larry']):
        self.cups = cups.split('\n')
        self.plant_abbrv = {'G': 'Grass', 'C': 'Clover', 'R': 'Radishes', 'V': 'Violets'}
        self.students = sorted(students)

    def plants(self, student):
        idx = self.students.index(student) * 2
        asd = self.cups[0][idx:idx+2] + self.cups[1][idx:idx+2]
        return [self.plant_abbrv[letter] for letter in asd]
