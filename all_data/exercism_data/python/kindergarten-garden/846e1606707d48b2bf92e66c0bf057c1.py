class Garden:

    plants_map = {
        p[0]: p for p in [
            'Grass', 'Clover', 'Radishes', 'Violets'
        ]
    }

    students = [
        'Alice', 'Bob', 'Charlie', 'David',
        'Eve', 'Fred', 'Ginny', 'Harriet',
        'Ileana', 'Joseph', 'Kincaid', 'Larry'
    ]

    def __init__(self, diagram, students=None):
        self.layout = {}
        self.students = sorted(students or self.students)
        for row in diagram.split('\n'):
            for n, i in enumerate(range(0, len(row), 2)):
                self.layout.setdefault(
                    self.students[n], []
                ).extend(
                    self.plants_map[p] for p in row[i:i+2]
                )

    def plants(self, student):
        return self.layout[student]
