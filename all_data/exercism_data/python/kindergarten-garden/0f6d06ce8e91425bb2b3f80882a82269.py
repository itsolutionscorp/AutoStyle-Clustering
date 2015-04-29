TRANS_MAP = {'G' : 'Grass', 'C': 'Clover',
             'R' : 'Radishes', 'V' : 'Violets'}
             
STUDENTS = ['Alice', 'Bob', 'Charlie', 'David',
            'Eve', 'Fred', 'Ginny', 'Harriet',
            'Ileana', 'Joseph', 'Kincaid', 'Larry']

class Garden:
    def __init__(self, map_string, students=STUDENTS):
        
        self.plant_config = [list(ms) for ms in map_string.split('\n')]
        self.students = sorted(students)
        
    def plants(self, student):
        
        index = 2 * self.students.index(student)

        return [TRANS_MAP[plant] for row in self.plant_config
                        for plant in row[index:index+2]]
