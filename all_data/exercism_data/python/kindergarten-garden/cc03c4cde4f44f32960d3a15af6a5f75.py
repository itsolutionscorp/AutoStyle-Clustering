class Garden(object):

    plantTypes = {'G':'Grass', 'C':'Clover', 'R':'Radishes', 'V':'Violets'}

    def __init__(self, plantMap, students=None):

        if not students:
            students = 'Alice Bob Charlie David Eve Fred Ginny Harriet Ileana Joseph Kincaid Larry'.split()
        else:
            students.sort()
        
        garden = tuple(plantMap.split('\n'))
        gardenSplit = [garden[0][i:i+2] + garden[1][i:i+2] for i in xrange(0,2*len(students),2)]

        self.ownership = {s: [self.plantTypes[p] for p in gardenSplit[idx]] for idx, s in enumerate(students)}

    def plants(self, s):

        return self.ownership[s]
