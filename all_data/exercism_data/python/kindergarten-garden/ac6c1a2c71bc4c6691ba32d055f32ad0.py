class Garden(object):
    """An object implementing a Kindergarten
    Garden."""
    def __init__(self, cup_string, students=None):
        self.garden_rows = cup_string.split('\n')

        if students:
            self.class_list = sorted(students)
        else:
            self.class_list = [
                "Alice", "Bob", "Charlie", "David",
                "Eve", "Fred", "Ginny", "Harriet",
                "Ileana", "Joseph", "Kincaid", "Larry"
            ]

        self.plants_dict = {
            "R": "Radishes",
            "C": "Clover",
            "G": "Grass",
            "V": "Violets"
        }

    def plants(self, child_name):
        index = 2 * self.class_list.index(child_name)
        child_plant_labels = [
            self.garden_rows[0][index],
            self.garden_rows[0][index + 1],
            self.garden_rows[1][index],
            self.garden_rows[1][index + 1]
        ]
        child_plants = [self.plants_dict[label] for label in child_plant_labels]
        return child_plants
