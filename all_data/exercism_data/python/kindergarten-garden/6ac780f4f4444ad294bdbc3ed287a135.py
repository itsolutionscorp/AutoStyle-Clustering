# Kindergarten Garden

class Garden(object):
    def __init__(self, plants_layout, 
                 students = ['Alice', 'Bob', 'Charlie', 'David', 'Eve', 
                                  'Fred', 'Ginny', 'Harriet', 'Ileana', 
                                  'Joseph', 'Kincaid', 'Larry']):
                                                           
        self.plants_layout = plants_layout
        
        self.students_list = sorted(students)
        
        self.plants_abbreviation = {'G': 'Grass', 'C': 'Clover', 
                                    'R': 'Radishes', 'V': 'Violets'}

    def plants(self, student):
        plants_layout_list = self.plants_layout.split()
        student_index = self.students_list.index(student)
        student_plants = []
        
        for i in range(2):
            plants_in_row = plants_layout_list[i][student_index * 2: \
                            student_index * 2 + 2]
            student_plants += plants_in_row
        
        print(student_plants)
            
        for i in range(4):
            student_plants[i] = self.plants_abbreviation[student_plants[i]]
            
        return student_plants
