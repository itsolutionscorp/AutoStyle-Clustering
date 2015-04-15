class Garden:
    flowers = {"G": "Grass", "C": "Clover", "R": "Radishes", "V": "Violets"}

    def __init__(self, plant_string, students=["Alice", "Bob", "Charlie", "David",
                                                "Eve", "Fred", "Ginny", "Harriet",
                                                "Ileana", "Joseph", "Kincaid", "Larry"]):
        self.student_list = sorted(students)
        self.plant_order = plant_string.split()

    def plants(self, student):
        plant_list = []
        list_index = self.student_list.index(student)*2
        for item in self.plant_order:
            plant_list.extend([self.flowers.get(c) for c in ''.join(c for c in item[list_index:list_index+2])])
        return plant_list



