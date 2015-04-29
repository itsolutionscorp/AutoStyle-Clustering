from itertools import chain

class Garden:
    def __init__(self, diagram, students=None):
        self.diagram = diagram.split('\n')
        if students == None:
            self.students = ['Alice', 'Bob', 'Charlie', 'David', 'Eve', 'Fred',
                             'Ginny', 'Harriet', 'Ileana', 'Joseph', 'Kincaid',
                             'Larry']
        else:
            self.students = sorted(students)
        self.plantCodes = {'V': 'Violets', 'R': 'Radishes', 'G': 'Grass',
                       'C': 'Clover'}

    def plants(self, student):
        stindex = self.students.index(student)
        stdiagram = chain.from_iterable((row[stindex*2:stindex*2+2] for row in self.diagram))
        return [self.plantCodes[letter] for letter in stdiagram]
