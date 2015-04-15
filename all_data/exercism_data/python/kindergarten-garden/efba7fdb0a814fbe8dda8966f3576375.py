class Garden():
    plant={"G":"Grass","C":"Clover","R":"Radishes","V":"Violets",}
    garden1=[]
    garden2=[]
    #sets up the pots
    def __init__(self,g,*students):
        self.students=students or ["Alice","Bob","Charlie","David","Eve", "Fred", "Ginny", "Harriet","Ileana", "Joseph", "Kincaid","Larry"]
        
        second=True
        for c in g:
            if(c =="\n"):
                second=False
            elif(second):
                self.garden1.append(c)
            else:
                self.garden2.append(c) 
                
        
        
    def plants (self,name):
        childPlants=[]
        start=self.students.index(name)*2
        childPlants.append(self.plant[self.garden1[start]])
        childPlants.append(self.plant[self.garden1[start+1]])
        childPlants.append(self.plant[self.garden2[start]])
        childPlants.append(self.plant[self.garden2[start+1]])
        
        return childPlants 
