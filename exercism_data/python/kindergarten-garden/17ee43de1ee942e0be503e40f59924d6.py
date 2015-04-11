__author__ = 'DIA'

PLANT_MAP = {"R": 'Radishes', "G": 'Grass', "C": "Clover", "V": "Violets"}


class Garden:
    def __init__(self, _rows,
                 students=["Alice", "Bob", "Charlie", "David", "Eve", "Fred", "Ginny", "Harriet", "Ileana", "Joseph",
                           "Kincaid", "Larry"]):
        self.rows = _rows.split()
        # Is there a single list comprehension for adj_rows??
        self.adj_rows = []
        for row in self.rows:
            self.adj_rows.append([row[i:i + 2] for i in range(0, len(row) - 1, 2)])
        self.students = students

    def plants(self, student):
        student_idx = self.students.index(student)
        plants_by_students = [row[i] for row in self.adj_rows for i, x in enumerate(row) if i == student_idx]
        return [PLANT_MAP[plant] for plants in plants_by_students for plant in plants]


garden = Garden("VCRRGVRG\nRVGCCGCV",
                students="Samantha Patricia Xander Roger".split())
print(garden.plants("Patricia"))
print(garden.plants("Xander"))
