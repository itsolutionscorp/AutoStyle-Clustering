class Garden(object):

    _plant_names = {"G": "Grass", "C": "Clover", "R": "Radishes", "V": "Violets"}
    _default_students = ["Alice", "Bob" , "Charlie", "David", "Eve", "Fred", "Ginny", "Harriet", "Ileana", "Joseph",
                         "Kincaid", "Larry"]

    def __init__(self, plants, per_student=4, students=None):
        self._plant_rows = plants.split()
        self._students = students or self._default_students
        self._students.sort()
        self._per_row = per_student / len(self._plant_rows)
        self._student_plants = self._create_plant_idx(self._plant_rows, self._students, self._per_row)

    @staticmethod
    def _create_plant_idx(plant_rows, students, per_row):
        student_plants = {}
        for idx, student in enumerate(students):
            plants = []
            plant_idx = idx * per_row
            for row in plant_rows:
                plants += [Garden._plant_names[p] for p in row[plant_idx: plant_idx + per_row]]
            student_plants[student] = plants
        return student_plants

    def plants(self, name):
        return self._student_plants[name]
