class Garden(object):
    garden_dict = {'G': 'Grass', 'C': 'Clover', 'R': 'Radishes', 'V': 'Violets'}

    def __init__(self, arrangement, students=('Alice', 'Bob', 'Charlie', 'David', 'Eve', 'Fred', 'Ginny', 'Harriet',
                                              'Ileana', 'Joseph', 'Kincaid', 'Larry')):
        self.arrangement = arrangement.split('\n')
        self.students = students

    def plants(self, student):
        plant_string = ''
        for row in self.arrangement:
            plant_string += row[(2*self.students.index(student)):(2*self.students.index(student)+2)]
        return [self.garden_dict[plant_letter] for plant_letter in plant_string]
