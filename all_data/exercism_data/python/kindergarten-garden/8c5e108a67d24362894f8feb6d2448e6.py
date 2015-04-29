class Garden:
    studentdict = {"Alice": 0, "Bob": 2, "Charlie": 4, "David": 6,
                   "Eve": 8, "Fred": 10, "Ginny": 12, "Harriet": 14,
                   "Ileana": 16, "Joseph": 18, "Kincaid": 20, "Larry": 22}

    def __init__(self, cups, students=studentdict):
        self.cups = cups.split()
        self.plantsdict = {'V': 'Violets', 'R': 'Radishes',
                           'C': 'Clover', 'G': 'Grass'}

        if type(students) is not dict:
            x = 0
            stdict = {}
            students = sorted(students)
            for i in students:
                stdict[i] = x
                x += 2
            self.students = stdict
        else:
            self.students = students

    def plants(self, studentname):
        outputL = []
        studentpos = self.students[studentname]
        for i in self.cups:
            outputL += self.plantsdict[i[studentpos]], self.plantsdict[i[studentpos + 1]]
        return outputL
