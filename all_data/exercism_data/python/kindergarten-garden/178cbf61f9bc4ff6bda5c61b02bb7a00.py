class Garden():
    def __init__(self, plants, students=['Alice','Bob','Charlie','David','Eve','Fred','Ginny','Harriet','Ileana','Joseph','Kincaid','Larry']):
        students.sort()
        self.students = {}
        position=0
        for student in students:
            self.students[student] = position
            position += 2
        self.rows_of_plants = plants.split('\n')

    def plants(self, student):
        plant_names = {'V':'Violets', 'R':'Radishes', 'G':'Grass', 'C':'Clover'}
        result = []
        for row_of_plants in self.rows_of_plants:
            result.append(plant_names[row_of_plants[self.students[student]]])
            result.append(plant_names[row_of_plants[self.students[student]+1]])
        return result
