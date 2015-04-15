class Garden:
    def __init__(self, cups, students  = []):
        self.cup = cups.split()
        self.student = sorted(students)

    def plants(self, name):
        result = []
        plant = {'G':'Grass', 'C':'Clover', 'R':'Radishes', 'V':'Violets'}

        pos = 0
        if self.student:
            pos = self.student.index(name) * 2
        else:
            pos = (ord(name[0]) - ord('A')) * 2

        for i in [0, 1]:
            for j in [pos, pos+1]:
                result.append(plant[self.cup[i][j]])

        return result
