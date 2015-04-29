class Garden:
    children = ["Alice", "Bob", "Charlie", "David", "Eve", "Fred", "Ginny",
                "Harriet", "Ileana", "Joseph", "Kincaid", "Larry"]
    plant_dict = {"V": "Violets", "C": "Clover", "G": "Grass", "R": "Radishes"}

    def __init__(self, garden_string, students=[0]):
        self.garden = []
        if not students == [0]:
            self.children = sorted(students)
        for i, line in enumerate(garden_string.splitlines()):
            self.garden.append(list())
            for code in line:
                self.garden[i].append(self.plant_dict[code])

    def plants(self, child):
        child_pos = self.children.index(child) * 2
        child_plants = []
        for row in self.garden:
            for plant in row[child_pos:child_pos + 2]:
                child_plants.append(plant)
        return child_plants
