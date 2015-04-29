students_name = ['Alice', 'Bob', 'Charlie', 'David', 'Eve',
               'Fred', 'Ginny', 'Harriet', 'Ileana',
               'Joseph', 'Kincaid', 'Larry']
plants = ['Grass', 'Clover', 'Radishes', 'Violets']

class Garden:
    def __init__(self, garden, students=students_name):
        students.sort()
        self.child_map = dict(zip(students, range(len(students))))
        self.plant_map = dict(zip([p[0] for p in plants],
                                  plants))
        self.rows = garden.split('\n')

    def plants(self, child):
        child = self.child_map[child]
        res = []
        for row in self.rows:
            res += [self.plant_map[p] for p in row[2*child:2*child+2]]
        return res
