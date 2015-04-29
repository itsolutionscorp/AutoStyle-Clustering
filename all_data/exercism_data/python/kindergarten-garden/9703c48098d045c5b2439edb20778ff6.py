class Garden:
    
    def __init__(self, flowers, students=[]):
        self.garden = flowers.replace("\n", "")
        self.flowerMap = {  'C' :   "Clover",\
                            'G' :   "Grass",\
                            'V' :   "Violets",\
                            'R' :   "Radishes"  }
        if not students:
            self.students = \
                        [   "Alice", "Bob", "Charlie", "David",
                            "Eve", "Fred", "Ginny", "Harriet",
                            "Ileana", "Joseph", "Kincaid", "Larry"  ]
        else:
            self.students = sorted(students)

    def _getPlants(self, index):
        start = index * 2
        startSecondRow = start + len(self.garden) / 2
        flowers = self.garden[start:start + 2] + \
            self.garden[startSecondRow:startSecondRow + 2]
        return list(flowers)
        

    def plants(self, student):
        plantList = self._getPlants(self.students.index(student))
        return [ self.flowerMap[x] for x in plantList ]
