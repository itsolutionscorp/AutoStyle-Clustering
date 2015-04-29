plant_list = {'G': 'Grass', 'C': 'Clover', 'V': 'Violets', 'R': 'Radishes'}


class Garden:
    def __init__(self, plants, students=None):
        if not students: students = ['Alice', 'Bob', 'Charlie', 'David', 'Eve', 'Fred', 'Ginny', 'Harriet',
                                     'Ileana', 'Joseph', 'Kincaid', 'Larry']
        self.plant_rows = plants.split('\n')
        students.sort()
        self.students = students

    def plants(self, student):
        n = self.students.index(student) * 2
        s = []

        return [plant_list[self.plant_rows[i][j]] for i in [0, 1] for j in [n, n+1]]
