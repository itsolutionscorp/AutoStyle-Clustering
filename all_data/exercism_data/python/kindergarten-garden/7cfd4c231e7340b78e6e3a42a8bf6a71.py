PLANT_NAMES = {
        'C': 'Clover',
        'G': 'Grass',
        'R': 'Radishes',
        'V': 'Violets'
}

class Garden():


    def __init__(
            self,
            garden_diagram,
            students="Alice Bob Charlie David Eve Fred Ginny Harriet Ileana Joseph Kincaid Larry".split()
        ):
        self.plant_dict = {}
        first_row, second_row = garden_diagram.split('\n')
        students.sort()
        for i, student in enumerate(students):
            self.plant_dict[student] = first_row[2*i:2*i+2] + second_row[2*i:2*i+2]


    def plants(self, name):
        return [PLANT_NAMES[p] for p in self.plant_dict[name]]
