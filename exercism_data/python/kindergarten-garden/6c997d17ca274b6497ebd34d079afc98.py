plant_dict = {
    'C' : "Clover",
    'G' : "Grass",
    'R' : "Radishes",
    'V' : "Violets"
    }

student_names = ["Alice", "Bob", "Charlie", "David", "Eve", "Fred", "Ginny",
                 "Harriet", "Ileana", "Joseph", "Kincaid", "Larry"]

class Garden(object):
    
    def __init__(self, plants, students = student_names, dic = plant_dict):
        self.rows = plants.split()
        self.students = sorted(students)
        self.dic = dic
        
    def plants(self, student):
        ind = self.students.index(student)         
        student_plants = [ self.rows[0][2 * ind], self.rows[0][2*ind +1],
                 self.rows[1][2 * ind], self.rows[1][2*ind +1] ]
        return [ self.dic[p] for p in student_plants ]
