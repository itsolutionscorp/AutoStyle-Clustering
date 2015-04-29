class Garden():
    def __init__(self, gardenStr, students=None):
        self.gardenStr = gardenStr
        if students is None:
            self.students = ["Alice", "Bob", "Charlie", "David", "Eve", "Fred", "Ginny", "Harriet",
                             "Ileana", "Joseph", "Kincaid", "Larry"]
        else:
            self.students = students
        self.students.sort()
        self.plantsDict = {"G":"Grass", "C":"Clover", "V":"Violets", "R":"Radishes"}

    def plants(self, student):
        if student not in self.students:
            raise ValueError("Invalid kid name")
        i = self.students.index(student)
        rows = self.gardenStr.split()
        plantsStr = rows[0][i * 2:i * 2 + 2] + rows[1][i * 2:i * 2 + 2]
        res = [self.plantsDict[p] for p in plantsStr]
        return res




