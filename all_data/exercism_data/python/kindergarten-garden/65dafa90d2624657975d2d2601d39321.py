defaultStudents = ['Alice','Bob','Charlie','David','Eve','Fred','Ginny','Harriet','Ileana','Joseph','Kincaid','Larry']
plantMap ={
    'V':'Violets',
    'R':'Radishes',
    'C':'Clover',
    'G':'Grass'
}

class Garden:
    def __init__(self, garden, students=defaultStudents):
        self.row1, self.row2 = garden.split('\n')
        self.studentMap = dict(zip(sorted(students),range(len(students))))

    def plants(self, student):
        idx = self.studentMap[student]*2
        return [plantMap[plant] for plant in self.row1[idx:idx+2] + self.row2[idx:idx+2]]
