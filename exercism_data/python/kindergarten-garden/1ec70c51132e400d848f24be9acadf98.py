class Garden:
    def __init__(self, pots, students=""):
        self.front = pots.split("\n")[0]
        self.back = pots.split("\n")[1]
        
        self.students = sorted(students)
        
    def plants(self, name):
        pots = ""
        partial = "VRCG"
        full = "Violets Radishes Clover Grass".split()
        
        if not self.students:
            index = "ABCDEFGHIJKL".index(name[0])
        else:
            index = self.students.index(name)
        pots += self.front[index*2:index*2 + 2]
        pots += self.back[index*2:index*2 + 2]
        
        pots_full = []
        for pot in pots:
            index = partial.index(pot)
            pots_full.append(full[index])
        
        return pots_full
