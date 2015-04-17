class Garden:
    plantDict = {
        'C': 'Clover',
        'G': 'Grass',
        'R': 'Radishes',
        'V': 'Violets'
    }

    def __init__(self, row, students=None):
        r = row.split()
        r = map(list, r)
        first = r[0]
        second = r[1]

        if students:
            self.constructTable(first, second, [s[0] for s in sorted(students)])
        else:
            s = [chr(ord('A') + i/2) for i in xrange(0, len(first), 2)]
            self.constructTable(first, second, s)

    def constructTable(self, first, second, students):
        self.table = {}
        for i in xrange(0, len(first), 2):
            l = first[i:i+2] + second[i:i+2]
            self.table[students[i/2]] = [self.plantDict[p] for p in l]
        
    def plants(self, name):
        return self.table[name[0]]