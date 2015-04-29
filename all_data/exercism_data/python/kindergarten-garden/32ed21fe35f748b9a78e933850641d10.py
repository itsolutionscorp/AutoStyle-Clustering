class Garden:
    DEFAULT_STUDENTS = 'Alice Bob Charlie David Eve Fred Ginny ' \
                       'Harriet Ileana Joseph Kincaid Larry'.split()

    def __init__(self, diagram, students=DEFAULT_STUDENTS):
        self.diagram = diagram.split()
        self.students = sorted(students)

    def plants(self, name):
        start = self.students.index(name) * 2
        encoded_plants = self.diagram[0][start:start + 2] + self.diagram[1][start:start + 2]

        trans = dict(R='Radishes', C='Clover', G='Grass', V='Violets')
        return [trans[code] for code in encoded_plants]
