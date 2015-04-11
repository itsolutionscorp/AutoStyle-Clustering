class Garden(object):

    plantDict = dict(V='Violets', R='Radishes', G='Grass', C='Clover')
    origGroup = ['Alice', 'Bob', 'Charlie', 'David', 'Eve', 'Fred', 'Ginny',
                 'Harriet', 'Ileana', 'Joseph', 'Kincaid', 'Larry']
    
    def __init__(self, row_string, students=origGroup):
        
        students = sorted(students)
        rows = row_string.splitlines()
        
        self.plant_groups = {student:''.join([row[2*i : 2*i+2] for row in rows])
                            for i, student in enumerate(students)
                            }
        
    def plants(self, name):

        return [self.plantDict[plant]
                for plant in self.plant_groups[name]]
