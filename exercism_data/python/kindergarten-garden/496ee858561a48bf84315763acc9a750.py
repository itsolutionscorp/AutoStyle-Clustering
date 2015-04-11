class Garden:

    DEFAULT_STUDENTS = [
        'Alice', 'Bob', 'Charlie', 'David',
        'Eve', 'Fred', 'Ginny', 'Harriet',
        'Ileana', 'Joseph', 'Kincaid', 'Larry'
    ]

    PLANTS = {
        'G': 'Grass',
        'C': 'Clover',
        'R': 'Radishes',
        'V': 'Violets'
    }

    def __init__(self, rows, students=[]):
        self.rows = rows.split()
        self.students = sorted(students) if students else self.DEFAULT_STUDENTS

    def plants(self, name):
        start_index = self.students.index(name) * 2
        index_range = slice(start_index, start_index + 2)
        plants = self.rows[0][index_range] + self.rows[1][index_range]

        return [self._get_plant_name(plant) for plant in plants]

    def _get_plant_name(self, plant):
        return self.PLANTS[plant]
