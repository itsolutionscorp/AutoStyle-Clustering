CHILDREN = ("Alice Bob Charlie David Eve Fred Ginny Harriet Ileana Joseph "
            "Kincaid Larry".split())
PLANTS = "Grass Clover Radishes Violets".split()
PLANTS = dict((plant[0], plant) for plant in PLANTS)

class Garden(object):
    def __init__(self, state, students=CHILDREN):
        self.state = state.split("\n")
        self.students = sorted(students)

    def plants(self, child):
        i = self.students.index(child) * 2
        return [PLANTS[letter] for letter in self.state[0][i:i+2] +
                                             self.state[1][i:i+2]]
