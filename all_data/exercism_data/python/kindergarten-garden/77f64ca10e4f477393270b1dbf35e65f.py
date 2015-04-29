class Garden:
    plant_dict = {'G': 'Grass',
                  'C': 'Clover',
                  'R': 'Radishes',
                  'V': 'Violets'}

    def __init__(self, diagram, students=None):
        self.diagram = diagram.split('\n')
        if students:
            self.students = students
            self.students.sort()
        else:
            self.students = "Alice Bob Charlie David Eve Fred Ginny Harriet " \
                            "Ileana Joseph Kincaid Larry".split()

    def plants(self, student):
        pos = self.students.index(student) * 2
        student_plants = ''.join([x[pos:pos+2] for x in self.diagram])
        return [Garden.plant_dict[x] for x in student_plants]

if __name__ == "__main__":
    garden = Garden("VCRRGVRG\nRVGCCGCV",
                    students="Samantha Patricia Xander Roger".split())
    print(garden.students)
    print(garden.diagram)
    print(garden.plants('Patricia'))
    # print(garden.plants('Bob'))
    # print(garden.plants('Charlie'))
