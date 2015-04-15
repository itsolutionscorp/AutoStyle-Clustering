import string


class Garden:
    def __init__(self, plant_diagram, students=[], planted=['grass', 'clover', 'radishes', 'violets']):
        self.plant_diagram = plant_diagram
        self.plant_rows = self.parse_plants(self.plant_diagram)
        self.students = students
        self.students.sort()
        self.planted = planted
        self.plant_names = {i.capitalize()[0]: i.capitalize() for i in self.planted}

    def plants(self, student):
        student_index = self.get_student_index(student)
        return [self.plant_names[self.plant_rows[0][student_index*2]], self.plant_names[self.plant_rows[0][student_index*2+1]],
                self.plant_names[self.plant_rows[1][student_index*2]], self.plant_names[self.plant_rows[1][student_index*2+1]]]

    def parse_plants(self, plant_diagram):
        rows = plant_diagram.split("\n")
        return rows

    def get_student_index(self, student):
        try:
            return self.students.index(student)
        except ValueError:
            return string.ascii_lowercase.index(student[0].lower())
