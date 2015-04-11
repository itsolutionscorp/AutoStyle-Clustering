""" module garden for exercism.io programming training """


class Garden():
    def __init__(self, plants, 
             students = '''Alice Bob Charlie David Eve Fred Ginny Harriet 
             Ileana Joseph Kincaid Larry'''.split()):
        self.rows = plants.splitlines()
        self.students = sorted(students)
    
    
    def plants(self, student):
        p_trans = {'C': 'Clover', 'G': 'Grass', 'R': 'Radishes', 'V': 'Violets'}
        index = self.students.index(student)
        own_plants = ''.join(i[index*2:(index+1)*2] for i in self.rows)
        return [p_trans[i] for i in own_plants]
