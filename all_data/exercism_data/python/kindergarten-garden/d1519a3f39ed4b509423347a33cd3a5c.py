from collections import defaultdict


class Garden:
    plants_map = {
        'V': 'Violets',
        'R': 'Radishes',
        'G': 'Grass',
        'C': 'Clover'
    }

    names = ['Alice', 'Bob', 'Charlie', 'David',
             'Eve', 'Fred', 'Ginny', 'Harriet',
             'Ileana', 'Joseph', 'Kincaid', 'Larry']

    def __init__(self, plants, students=names):
        self.students = sorted(students)
        rows = plants.split()
        self.columns = zip(*rows)

    def plants(self, name):
        results = defaultdict(list)

        for i, (x, y) in enumerate(
                [(x, x+1) for x in range(0, len(self.columns), 2)]):
            student = self.students[i]
            plants = results[student] if student in results else []

            x = self.columns[x]
            y = self.columns[y]

            plants.extend([x[0], y[0], x[1], y[1]])
            results[student] = [self.plants_map[p] for p in plants]

        return results[name]
