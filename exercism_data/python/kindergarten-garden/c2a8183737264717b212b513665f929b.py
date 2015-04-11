class Garden:
    def __init__(self, rows, students = None):
        self.rows = rows.split()
        self.students = sorted(students) if students else '''Alice 
                        Bob Charlie David Eve Fred Ginny Harriet 
                        Ileana Joseph Kincaid Larry'''.split()
        self.plant = {'V' : 'Violets',
                      'C' : 'Clover',
                      'R' : 'Radishes',
                      'G' : 'Grass'
                      }

    def plants(self, student):
        i = self.students.index(student) * 2
        return [self.plant[self.rows[0][i]],
                self.plant[self.rows[0][i+1]],
                self.plant[self.rows[1][i]],
                self.plant[self.rows[1][i+1]],
                ]
