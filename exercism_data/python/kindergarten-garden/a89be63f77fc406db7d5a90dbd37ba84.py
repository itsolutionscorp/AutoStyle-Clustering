class Garden:

    def __init__(self, garden, students = ['Alice', 'Bob', 'Charlie', 'David', 'Eve', 'Fred', 'Ginny',
    'Harriet', 'Ileana', 'Joseph', 'Kincaid', 'Larry']):
        self.garden = garden.split("\n")
        students.sort()
        self.students = students
        self.garden_one = list(self.garden[0])
        self.garden_two = list(self.garden[1])

    def plants(self, student):
        PLANT_KEY = {'R':'Radishes', 'C':'Clover', 'V':'Violets', 'G':'Grass'}
        plant_names = []
        for i, name in enumerate(self.students):
            if student == name:
                plant_letters = self.garden_one[i*2:i*2+2] + self.garden_two[i*2:i*2+2]
                for letter in plant_letters:
                    plant_names.append(PLANT_KEY[letter])
        return plant_names
