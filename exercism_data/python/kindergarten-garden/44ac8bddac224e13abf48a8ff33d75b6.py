class Garden:
    plants = set(("Grass", "Violets", "Clover" ,"Radishes"))

    code_to_plant = dict((p[0], p) for p in plants)

    def __init__(self, plant_setup, students = None):
        if students is None:
            students = ["Alice", "Bob", "Charlie", "David", "Eve", "Fred",
                        "Ginny", "Harriet", "Ileana", "Joseph", "Kincaid",
                        "Larry"]
        else:
            students = sorted(students)

        self._student_to_index = dict((item[::-1]
                                       for item in enumerate(students)))
        self.rows = plant_setup.splitlines()

    def plants(self, name):
        start_column = self._student_to_index[name]*2
        codes = [c for row in self.rows
                 for c in row[start_column:start_column+2]]

        return [Garden.code_to_plant[c] for c in codes]
