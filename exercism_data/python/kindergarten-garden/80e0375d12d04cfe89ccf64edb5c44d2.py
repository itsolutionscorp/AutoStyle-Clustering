class Garden:

    def __init__(self, garden, students=None):
        if students is None:
            self.students = 'Alice Bob Charlie David Eve Fred Ginny Harriet Ileana Joseph Kincaid Larry'.split()
        else:
            self.students = sorted(students)

        self.garden = garden.split('\n')
        self.plants_dict = {'G':'Grass', 'C':'Clover', 'R':'Radishes', 'V':'Violets'}

    def plants(self, student):
        garden_list = []
        for row in self.garden:
            garden_list += row[2 * self.students.index(student)]
            garden_list += row[2 * self.students.index(student) + 1]

        return [self.plants_dict.get(item) for item in garden_list]
