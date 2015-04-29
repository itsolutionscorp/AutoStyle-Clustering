class Garden(object):

    names = ["Alice", "Bob", "Charlie", "David",
             "Eve", "Fred", "Ginny", "Harriet",
             "Ileana", "Joseph", "Kincaid", "Larry"]

    plant_names = {
        "C": "Clover",
        "V": "Violets",
        "G": "Grass",
        "R": "Radishes"
    }

    def __init__(self, garden_str, students=None):
        students = sorted(students or Garden.names)

        row1, row2 = [list(row) for row in garden_str.split("\n")]

        self.garden_dict = {}
        for index, name in enumerate(students):
            start = index * 2
            plant_chars = row1[start:start + 2] + row2[start:start + 2]
            self.garden_dict[name] = [Garden.plant_names[char] for char in plant_chars]

    def plants(self, name):
        return self.garden_dict[name]
