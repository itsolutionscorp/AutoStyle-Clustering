class Garden:
    children = ['Alice', 'Bob', 'Charlie', 'David',
                'Eve', 'Fred', 'Ginny', 'Harriet',
                'Ileana', 'Joseph', 'Kincaid', 'Larry']
    plant_names = dict(V='Violets', R='Radishes', C='Clover', G='Grass')

    def __init__(self, rows):
        self.plants_in_rows = [self.plant_names[c] for row in rows.splitlines() for c in row]

    def plants(self, child):
        idx = self.children.index(child) * 4
        return self.plants_in_rows[idx:idx + 4]
