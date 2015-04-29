class Garden:

    letter_to_plant_name = {'G': 'Grass', 'C': 'Clover', 'R': 'Radishes', 'V': 'Violets'}

    def __init__(self, window_plant_letters, students=['Alice', 'Bob', 'Charlie', 'David',
                                                       'Eve', 'Fred', 'Ginny', 'Harriet',
                                                       'Ileana', 'Joseph', 'Kincaid', 'Larry']):
        self.window_plant_letters = window_plant_letters
        self.student_names = students[:]
        self.student_names.sort()
        self.all_student_plants = []

        zipped = list(zip(*[list(s) for s in self.window_plant_letters.split()]))
        for j in range(0, len(zipped)-1, 2):
            self.all_student_plants.append([Garden.letter_to_plant_name[zipped[j][0]],
                                            Garden.letter_to_plant_name[zipped[j+1][0]],
                                            Garden.letter_to_plant_name[zipped[j][1]],
                                            Garden.letter_to_plant_name[zipped[j+1][1]]])

    def plants(self, student_name):
        return self.all_student_plants[self.student_names.index(student_name)]
