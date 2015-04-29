plant_dict = {"G": "Grass", "C":"Clover", "R":"Radishes", "V": "Violets"}

class Garden:

    def __init__(self, plants, students = ("Alice", "Bob", "Charlie", "David", "Eve", "Fred", "Ginny", "Harriet", "Ileana", "Joseph", "Kincaid", "Larry")):
        #split plant strings into groups of 2 characters
        plants_list = plants.split("\n")
        chunked_plants_1 = [plants_list[0][i:i+2] for i in range(0, len(plants_list[0]), 2)]
        chunked_plants_2 = [plants_list[1][i:i+2] for i in range(0, len(plants_list[1]), 2)]

        self.student_plants = {}
        for student,plants_row_1,plants_row_2 in zip(sorted(students), chunked_plants_1, chunked_plants_2):
            self.student_plants[student] = [plant_dict[plant] for plant in (plants_row_1 + plants_row_2)]

    def plants(self, student):
        return self.student_plants[student]
