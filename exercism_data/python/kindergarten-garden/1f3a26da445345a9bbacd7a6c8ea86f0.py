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
        self.cups_per_child = 2

    def plants(self, child_name):

        # Find the starting cup of each row for each child
        index = self.cups_per_child * self.class_list.index(child_name)

        # Grab the slices of each garden row
        # that correspond to each child's cup
        child_plant_label_lists = [row[index:index + self.cups_per_child]
                                   for row in self.garden_rows]

        # Stick those strings together
        child_plant_labels = ''.join(child_plant_label_lists)

        # Iterate over each character in the labels
        # and grab the original plant name
        child_plants = [self.plants_dict[label]
                        for label in child_plant_labels]
        return child_plants
