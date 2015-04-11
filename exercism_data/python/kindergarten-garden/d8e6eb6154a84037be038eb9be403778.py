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
            
        rowA, rowB = garden.split()
        assert len(rowA) == len(rowB), "Rows must have equal amounts of plants."

        items = ''
        for i in xrange(0,len(rowA),2):
            items += rowA[i:i+2] + rowB[i:i+2]

        i = 0
        self.garden = {}
        for student in students:
            self.garden[student] = [key[item] for item in items[i:i+4]]
            i += 4

    def plants(self, person):
        return self.garden[person]
