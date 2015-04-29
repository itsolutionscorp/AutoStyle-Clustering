class Garden:
    _plant_names = dict(C="Clover", G="Grass", R="Radishes", V="Violets")

    def __init__(self, rows_as_string, students=None):
        self.rows = rows_as_string.splitlines()
        self.students = (sorted(students) if students else
            ["Alice", "Bob", "Charlie", "David", "Eve", "Fred",
             "Ginny", "Harriet", "Ileana", "Joseph", "Kincaid", "Larry"])

    def plants(self, student):
        student_ix = self.students.index(student)
        plants = ''.join([row[2 * student_ix: 2 * student_ix + 2]
                          for row in self.rows])
        return [self._plant_names[plant]
                for plant in plants]
