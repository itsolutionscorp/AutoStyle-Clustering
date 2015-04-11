class Garden():
    
    kids = ['Alice', 'Bob', 'Charlie', 'David', 'Eve', 'Fred', 'Ginny', \
            'Harriet', 'Ileana', 'Joseph', 'Kincaid', 'Larry']
            
    plantnames = {'V':'Violets', 'R':'Radishes', 'C':'Clover', 'G':'Grass'}
            
    def __init__(self,garden, students=kids):
        self.students = sorted(students)
        self.rows = garden.split('\n')

    def plants(self, kid):
        kidindex = self.students.index(kid)*2

        cups = ''.join([x[kidindex:kidindex+2] for x in self.rows])
        return [self.plantnames[letter] for letter in cups]
