plantNames = {'G': 'Grass', 'C': 'Clover', 'R': 'Radishes','V': 'Violets'}

defaultChildren = ['Alice', 'Bob', 'Charlie', 'David',
            'Eve', 'Fred', 'Ginny', 'Harriet',
            'Ileana', 'Joseph', 'Kincaid', 'Larry']


class Garden:
    rows = []
    students = []

    def __init__(self, gardenString="", students=defaultChildren):
        self.rows = gardenString.split("\n")
        students.sort()
        self.students = students


    def plants(self, child):
        return [plantNames[self.rows[0][self.students.index(child) * 2]],
        plantNames[self.rows[0][self.students.index(child) * 2 + 1]],
        plantNames[self.rows[1][self.students.index(child) * 2]],
        plantNames[self.rows[1][self.students.index(child) * 2 + 1]]
                ]
