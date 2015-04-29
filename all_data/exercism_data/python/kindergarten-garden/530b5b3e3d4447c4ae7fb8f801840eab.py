class Garden(object):
    STUDENTS=["Alice", "Bob", "Charlie", "David",
              "Eve", "Fred", "Ginny", "Harriet",
              "Ileana", "Joseph", "Kincaid", "Larry"]
    PLANTS = {
        'C': 'Clover',
        'G': 'Grass',
        'R': 'Radishes',
        'V': 'Violets',
    }

    def __init__(self, diagram, students=STUDENTS):
        self.diagram = diagram.split('\n')
        self.students = students
        # students are allotted plants in alphabetical order of their names
        self.students.sort()
        self.student_idx = {}
        for i in range(len(students)):
            self.student_idx[students[i]] = i*2
    
    def plants(self, student):
        idx = self.student_idx.get(student)
        return [self.PLANTS.get(self.diagram[i][j])
                for i in range(2)
                for j in range(idx, idx+2)]
        
