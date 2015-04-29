class Garden:
    def __init__(self, plants, students=['Alice','Bob','Charlie','David','Eve','Fred','Ginny','Harriet','Ilena','Joseph','Kincaid','Larry']):
        self.row1, self.row2 = plants.split()
        self.children = students
        self.children.sort()
    def plants(self, child):
        r = []
        offset = self._get_offset(child)
        r.append(self.row1[offset])
        r.append(self.row1[offset+1])
        r.append(self.row2[offset])
        r.append(self.row2[offset+1])
        return([self._lookup_plant_code(i) for i in r])
    def _lookup_plant_code(self, code):
        _p = ['Violets','Radishes','Clover','Grass']
        return([i for i in _p if i.startswith(code)][0])
    def _get_offset(self, child):
        return(2*self.children.index(child))
