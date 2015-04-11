import itertools

class Garden(object):
    
    original_list = [
                    'Alice', 'Bob', 'Charlie', 'David', 
                    'Eve', 'Fred', 'Ginny', 'Harriet', 
                    'Ileana', 'Joseph', 'Kincaid', 'Larry'
                    ]
    
    def __init__(self, design, students=original_list):
        
        self.students = students            
        students = sorted(students)
        
        self.design = design
        design = design.splitlines()
        line1 = list(design[0])
        line2 = list(design[1])
        
        line1 = [line1[i:i+2] for i in range(0, len(line1), 2)]
        line2 = [line2[i:i+2] for i in range(0, len(line2), 2)]
        
        group = zip(line1, line2)

        self.plant_list = zip(students, group)
        
    def plants(self, name):
        
        plant_dic = {
                    'V': 'Violets',
                    'R': 'Radishes',
                    'G': 'Grass',
                    'C': 'Clover'
                    }
        
        final = []
        for student, plants in self.plant_list:
            if student == name:
                for plant_pairs in plants:
                    for plant in plant_pairs:
                        final.append(plant_dic.get(plant))
            else:
                pass
                
        return final
