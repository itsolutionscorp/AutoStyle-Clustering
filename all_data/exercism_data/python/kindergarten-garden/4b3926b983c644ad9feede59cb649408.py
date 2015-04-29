PLANTS_PER_CHILD = 2
ROW_ONE = 0
ROW_TWO = 1

class Garden:
    def __init__(self, plant_order, students = None):
        self.plant_order = plant_order.strip().split('\n')

        self.children = [
                            "Alice",
                            "Bob",
                            "Charlie",
                            "David",
                            "Eve",
                            "Fred",
                            "Ginny",
                            "Harriet",
                            "Ileana",
                            "Joseph",
                            "Kincaid",
                            "Larry"
        ]

        if students:
            students.sort()
            self.children = students

        self.plant_types = {
            "G": "Grass",
            "C": "Clover",
            "R" : "Radishes",
            "V" : "Violets"
        }


    def plants(self, child):
        if child not in self.children:
            return None
        row_position = self.children.index(child)
        first_plant, second_plant  = row_position * PLANTS_PER_CHILD, (row_position * PLANTS_PER_CHILD) + 1
        return [
            self.plant_types[self.plant_order[ROW_ONE][first_plant]],
            self.plant_types[self.plant_order[ROW_ONE][second_plant]],
            self.plant_types[self.plant_order[ROW_TWO][first_plant]],
            self.plant_types[self.plant_order[ROW_TWO][second_plant]]
        ]
