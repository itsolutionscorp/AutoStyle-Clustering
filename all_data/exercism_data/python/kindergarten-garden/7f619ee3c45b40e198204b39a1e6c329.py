class Garden():
    children = ["Alice", "Bob", "Charlie", "David", "Eve", "Fred",
    "Ginny", "Harriet", "Ileana", "Joseph", "Kincaid", "Larry"]
    plant = ["Clover", "Grass", "Radishes", "Violets", "Empty"]
    ptran = ["C", "G", "R", "V", "."]#can just grab first char and caps?
    def __init__(self, holes, students=children):
        self.students = students
        self.students.sort()
        self.holes = [[],[]]
        #could skip this by padding appropriate amount of '.' instead
        for i in range(24):
            self.holes[0].append('.')
            self.holes[1].append('.')
        rows = holes.split()
        #asdf
        i = 0
        for row in rows:
            j = 0
            for char in row:
                self.holes[i][j] = char
                j+=1
            i+=1
    
    def plants(self, student):
        index = self.students.index(student)
        return [self.tplant(self.holes[0][index*2]),
                    self.tplant(self.holes[0][index*2 +1]),
                    self.tplant(self.holes[1][index*2]),
                    self.tplant(self.holes[1][index*2 +1])]

    def tplant(self, tp):
        return self.plant[self.ptran.index(tp)]
