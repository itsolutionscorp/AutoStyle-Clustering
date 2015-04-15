class Garden():
    def __init__(self, garden_string, students=None):
        if not students:
            students = ['Alice', 'Bob', 'Charlie', 'David',
                        'Eve', 'Fred', 'Ginny', 'Harriet',
                        'Ileana', 'Joseph', 'Kincaid', 'Larry']
        self.students = students
        self.students.sort()
        self.garden = garden_string.split('\n')
        self.n_rows = 2
        self.n_plants_per_student = 4
        self.n_plants_per_student_per_row = self.n_plants_per_student/self.n_rows
        self.conversion = {}
        self.conversion['V'] = 'Violets'
        self.conversion['R'] = 'Radishes'
        self.conversion['G'] = 'Grass'
        self.conversion['C'] = 'Clover'
        
        
    def plants(self, student):
        the_plants = []
        n = self.students.index(student)
        for r in xrange(self.n_rows):
            for c in xrange(self.n_plants_per_student_per_row):
                the_plants.append(self.conversion[self.garden[r][n*self.n_plants_per_student_per_row+c]])
        return the_plants
