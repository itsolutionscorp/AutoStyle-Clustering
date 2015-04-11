class Garden():
    DEFAULT_STUDENTS = "Alice Bob Charlie David Eve Fred Ginny Harriet Ileana Joseph Kincaid Larry".split()
    PLANT_KEY = {
        "G": "Grass",
        "C": "Clover",
        "R": "Radishes",
        "V": "Violets",
    }

    def __init__(self, plants, students=DEFAULT_STUDENTS):
        self.window_row, self.room_row = plants.split("\n")
        self.students = sorted(students)
        

    def plants(self, student):
        return [
            self.PLANT_KEY[self.window_row[self.cup(student)]],
            self.PLANT_KEY[self.window_row[self.cup(student)+1]],
            self.PLANT_KEY[self.room_row[self.cup(student)]],
            self.PLANT_KEY[self.room_row[self.cup(student)+1]]
            ]


    def cup(self, student):
        return self.students.index(student)*2
