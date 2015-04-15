
class Garden:

    def __init__(self,garden,students=["Alice","Bob","Charlie","David","Eve","Fred","Ginny","Harriet","Ileana","Joseph","Kincaid","Larry"]):

        self.plantdic={"V":"Violets","C":"Clover","G":"Grass","R":"Radishes"}
        self.garden1,self.garden2=garden.splitlines()
        self.students=sorted(students)
            
    def plants(self,student):
        idx = self.students.index(student)*2
        get = [self.garden1[idx],self.garden1[idx+1],self.garden2[idx],self.garden2[idx+1]]                                                                                
        return [self.plantdic[a] for a in get]
