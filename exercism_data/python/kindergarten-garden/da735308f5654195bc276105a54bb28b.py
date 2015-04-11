
kids = 'Alice Bob Charlie David Eve Fred Ginny Harriet Ileana Joseph Kincaid Larry'.split()
plants = {'V' : 'Violets',
          'C' : 'Clover',
          'R' : 'Radishes',
          'G' : 'Grass'
          }

class Garden:
    def __init__(self, rows, students = kids):
        self.rows = rows.split()
        self.students = sorted(students)

    def plants(self, student):
        i = self.students.index(student) * 2
        return [plants[self.rows[0][i]],
                plants[self.rows[0][i+1]],
                plants[self.rows[1][i]],
                plants[self.rows[1][i+1]],
                ]
