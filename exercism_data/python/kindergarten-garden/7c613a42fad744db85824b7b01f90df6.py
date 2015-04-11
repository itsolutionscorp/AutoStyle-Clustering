class Garden:
    def __init__(self, garden, students=None):
        self.garden = garden.upper().split('\n')
        self.types = {'G': 'Grass', 'C': 'Clover', 'R': 'Radishes',
                      'V': 'Violets'}
        if students:
            self.students = sorted([student for student in students])
        else:
            self.students = ['Alice', 'Bob', 'Charlie', 'David', 'Eve', 'Fred', 
                             'Ginny', 'Harriet', 'Ileana', 'Joseph', 'Kincaid', 
                             'Larry']

    def plants(self, student):
        grp = self.students.index(student)
        # Creates a list based on the index. Loops through all rows in the garden.
        return [self.types[p] for row in self.garden for p in row[grp*2:grp*2+2]]
