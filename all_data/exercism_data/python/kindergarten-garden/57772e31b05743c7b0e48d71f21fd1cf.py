class Garden:
    _plant_names = dict(C="Clover", G="Grass", R="Radishes", V="Violets")

    def __init__(self, rows_as_string, students=None):
        rows = rows_as_string.splitlines()
        students = (sorted(students) if students else
                    ["Alice", "Bob", "Charlie", "David", "Eve", "Fred", "Ginny",
                     "Harriet", "Ileana", "Joseph", "Kincaid", "Larry"])
        self._plants = {student: ''.join([row[2 * i: 2 * i + 2]
                                          for row in rows])
                        for i, student in enumerate(students)}

    def plants(self, student):
        return [self._plant_names[plant]
                for plant in self._plants[student]]
