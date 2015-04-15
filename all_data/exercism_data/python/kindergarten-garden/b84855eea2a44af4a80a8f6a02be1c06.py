class Garden(object):
    def __init__(self, garden, students = None):
        if not students:
            students = ['Alice','Bob','Charlie',
                        'David','Eve','Fred',
                        'Ginny','Harriet','Ileana',
                        'Joseph','Kincaid','Larry']
        else: students.sort()
        
        key = {'V':'Violets',
               'R':'Radishes',
               'C':'Clover',
               'G':'Grass'}
            
        rowI, rowJ = garden.split()
        assert len(rowI) == len(rowJ), "Rows must have equal amounts of plants."

        items = ''
        for i in xrange(0,len(rowI),2):
            items += rowI[i:i+2] + rowJ[i:i+2]

        i = 0
        self.garden = {}
        for student in students:
            self.garden[student] = items[i:i+4]
            i += 4

        for student in self.garden:
            self.garden[student] = [key[item] for item in self.garden[student]]

    def plants(self, person):
        return self.garden[person]
