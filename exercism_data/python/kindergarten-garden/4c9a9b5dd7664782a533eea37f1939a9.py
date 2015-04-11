class Garden():

    dict = {'R':'Radishes', 'C':'Clover', 'G':'Grass', 'V':'Violets'}

    def __init__(self, st, students='Alice Bob Charlie David Eve Fred Ginny Harriet Ileana Joseph Kincaid Larry'.split()):
        self.plantlst = st.split()
        students.sort()
        self.students = students

    def plants(self, nm):
        idx = 0
        ret = []
        for ch in self.students:
            if nm == ch:
                ret.append(self.dict[self.plantlst[0][idx*2]])
                ret.append(self.dict[self.plantlst[0][idx*2+1]])
                ret.append(self.dict[self.plantlst[1][idx*2]])
                ret.append(self.dict[self.plantlst[1][idx*2+1]])
                break

            idx += 1
        return ret
