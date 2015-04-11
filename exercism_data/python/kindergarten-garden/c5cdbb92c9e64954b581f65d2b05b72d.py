import collections

STUDENTS = ('Alice', 'Bob', 'Charlie', 'David', 'Eve', 'Fred', 'Ginny',
            'Harriet', 'Ileana', 'Joseph', 'Kincaid', 'Larry')

PLANTS = {'V': 'Violets', 'C': 'Clover', 'R': 'Radishes', 'G': 'Grass', }


class Garden(object):
    def __init__(self, rows, students=STUDENTS):
        students = sorted(students)
        rows = rows.split('\n')
        self.mapping = collections.defaultdict(list)
        n = 2
        for i, student in enumerate(students):
            for row in rows:
                for abb in row[i * n:n * (1 + i)]:
                    self.mapping[student].append(PLANTS[abb])

    def plants(self, student):
        return self.mapping[student]
