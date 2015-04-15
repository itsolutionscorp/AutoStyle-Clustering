class Garden(object):

    

    def __init__(self,plants,students=["A","B","C","D","E","F","G","H","I","J","K","L"]):
        row1,row2 = plants.split("\n")

        self.plantlist = row1.ljust(24,"!") + row2.ljust(24,"X")
        students = sorted(students) + ['!'] * (12 - len(students))
        self.children = "".join([n[0].upper() + n[0].upper() for n in students])
        
        self.children+=self.children

        
        self.veggies = {"V":"Violets",
            "G":"Grass",
            "C":"Clover",
            "R":"Radishes",
            "!":"ERROR" }

    def plants(self,name):
        
        return [ self.veggies[v] for n,v in zip(self.children,self.plantlist) if n==name[0].upper()]
        

        
        
