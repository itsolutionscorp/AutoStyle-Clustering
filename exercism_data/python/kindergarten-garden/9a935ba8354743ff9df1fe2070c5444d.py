from string import maketrans

class Garden:
    '''Assuming the garden only has 2 rows of plaint'''

    abbr = {
        'V':'Violets',
        'R':'Radishes',
        'G':'Grass',
        'C':'Clover'
    }

    def __init__(self, pattern, **kwargs):
        rows = pattern.split("\n")
        self.students = [
            'Alice', 'Bob', 'Charlie', 'David',
            'Eve', 'Fred', 'Ginny', 'Harriet',
            'Ileana', 'Joseph', 'Kincaid', 'Larry'
        ]
        if 'students' in kwargs:
            self.students = kwargs['students']
            self.students.sort()
        self.pattern = []
        for row in rows:
            self.pattern.append([x for x in row])

    def plants(self, name):
        index = self.students.index(name)
        result = [self.pattern[0][index * 2], self.pattern[0][index * 2 + 1]]
        result += [self.pattern[1][index * 2], self.pattern[1][index * 2 + 1]]
        return [self.abbr[x] for x in result]
