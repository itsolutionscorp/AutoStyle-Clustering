class Garden:
    def __init__(self, plants, students=('Alice', 'Bob', 'Charlie', 'David', 'Eve', 'Fred', 'Ginny', 'Harriet', 'Ileana', 'Joseph', 'Kincaid', 'Larry')):
        students = tuple(sorted(students)) 
        self._garden = dict( (student, []) for student in students )
        flowers = dict( (f[0], f) for f in ('Grass', 'Clover', 'Radishes', 'Violets'))
        for row in plants.split('\n'):
            for i, f in enumerate(row):
                self._garden[students[i/2]].append(flowers[f])
    
    def plants(self, who):
        return self._garden[who]
