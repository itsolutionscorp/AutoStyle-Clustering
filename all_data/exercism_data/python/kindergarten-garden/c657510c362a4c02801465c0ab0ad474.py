DEFAULT_STUDENTS  = ["Alice", "Bob", "Charlie", "David",
                    "Eve", "Fred", "Ginny", "Harriet",
                    "Ileana", "Joseph", "Kincaid", "Larry"]

PLANTS = {"R":"Radishes", "V":"Violets", "C":"Clover","G": "Grass"}

class Garden():
    def __init__(self, setup, students=DEFAULT_STUDENTS):
        self.garden = setup.split("\n")
        self.students = sorted(students)

    def plants(self, name):
        plant_list = ''
        for s in self.students:
            if s == name:
                plant_list += self.garden[0][self.students.index(s)*2:self.students.index(s)*2+2]
                plant_list += self.garden[1][self.students.index(s)*2:self.students.index(s)*2+2]
                return [PLANTS[x] for x in plant_list]
