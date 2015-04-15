class Garden:
    children = ['Alice', 'Bob', 'Charlie', 'David',
                'Eve', 'Fred', 'Ginny', 'Harriet',
                'Ileana', 'Joseph', 'Kincaid', 'Larry']
    plant_names = dict(V='Violets', R='Radishes', C='Clover', G='Grass')

    def __init__(self, rows, students = None):
        self.plants_in_rows = [ [self.plant_names[c] for c in row] for row in rows.splitlines()]
        if (students): self.children = sorted(students)

    def plants(self, child):
        idx = self.children.index(child) * 2
        return [plant for row in self.plants_in_rows for plant in row[idx:idx + 2]]
