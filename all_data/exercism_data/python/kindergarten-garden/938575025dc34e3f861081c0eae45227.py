class Garden(object):

    _plant_names = {"G": "Grass", "C": "Clover", "R": "Radishes", "V": "Violets"}

    def __init__(self, plantation, plants_per_student=4, students=["Alice", "Bob" , "Charlie", "David", "Eve", "Fred",
                 "Ginny", "Harriet", "Ileana", "Joseph", "Kincaid", "Larry"]):
        self._plant_rows = plantation.split()
        self._students = sorted(students)
        self._plants_per_row = plants_per_student / len(self._plant_rows)

    def plants(self, name):
        student_idx = self._students.index(name) * self._plants_per_row
        result = []
        for row in self._plant_rows:
            result += [self._plant_names[p] for p in row[student_idx:student_idx + self._plants_per_row]]
        return result
