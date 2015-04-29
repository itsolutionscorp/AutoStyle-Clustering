'''exer garden'''

class Garden:
    '''a class to determine plant ownership'''

    student_list = ['Alice', 'Bob', 'Charlie', 'David', 'Eve', 'Fred',
                    'Ginny', 'Harriet', 'Ileana', 'Joseph', 'Kincaid', 'Larry']
    plant_map = {'v': 'Violets', 'r': 'Radishes', 'c': 'Clover', 'g': 'Grass'}

    def __init__(self, garden_graph, students=None, plants_per_student=4):
        '''init the garden graph, set students'''

        if students is None:
            students = Garden.student_list
        students.sort()         # put in alpha order

        rows = [list(s) for s in garden_graph.lower().split('\n')]
        plants_per_row = plants_per_student / len(rows)

        self.map = {}
        for student in students:
            if rows[0]:               # while plants remain in 1st row
                self.map[student] = []
                for row in rows:
                    for _ in range(plants_per_row):    # per_row
                        self.map[student].append(Garden.plant_map[row.pop(0)])

        print self.map


    def plants(self, student):
        '''return the plants f,f,b,b for the desired student'''

        return self.map[student]
