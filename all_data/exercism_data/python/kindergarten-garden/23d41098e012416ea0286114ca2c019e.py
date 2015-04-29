class Garden(object):

    def __init__(self,garden,**kwargs):
        self.students = (sorted([y[0] for y in kwargs['students']]) if 'students' in kwargs
                         else [x for x in 'ABCDEFGHIJKL'])
        self.garden = garden.split('\n')

    def plants(self,student):
        results = []
        translate = {'V':'Violets', 'C':'Clover', 'R':'Radishes', 'G':'Grass'}
        student_index = self.students.index(student[0])
        for sil in self.garden:
            results.append(translate[sil[student_index * 2]])
            results.append(translate[sil[student_index * 2 + 1]])
        return results
