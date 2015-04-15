class Garden:
    def __init__(self, cups, students=dict()):
        self.cups = cups.split()
        self.plantsdict = {'V': 'Violets', 'R': 'Radishes',
                           'C': 'Clover', 'G': 'Grass'}
        self.names = ("Alice", "Bob", "Charlie", "David", "Eve", "Fred",
                      "Ginny", "Harriet", "Ileana", "Joseph", "Kincaid",
                      "Larry")

        if type(students) is not dict:
            count1 = tuple(range(0, len(students) * 2, 2))
            stdict = dict(zip(sorted(students), count1))
            self.students = stdict

        elif len(students) == 0:
            count1 = tuple(range(0, len(self.names) * 2, 2))
            stdict = dict(zip(self.names, count1))
            self.students = stdict

    def plants(self, studentname):
        outputL = []
        studentpos = self.students[studentname]
        for i in self.cups:
            outputL += self.plantsdict[i[studentpos]], self.plantsdict[i[studentpos + 1]]
        return outputL
