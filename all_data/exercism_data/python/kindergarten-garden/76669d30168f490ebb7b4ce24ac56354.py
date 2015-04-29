class Garden:
    students_list = ["Alice", "Bob", "Charlie", "David", "Eve", "Fred", "Ginny", "Harriet", "Ileana", "Joseph", "Kincaid", "Larry"]
    plant_dict = dict(zip(["C","V","G","R"],["Clover","Violets","Grass","Radishes"]))
    
    def __init__(self,garden_layout,**kwargs):
        self.garden_layout = garden_layout.split("\n")
        if len(kwargs) > 0 and "students" in kwargs:
            self.students_list = sorted(kwargs["students"])
        
    def plants(self,student):
        i = self.students_list.index(student)
        student_plants = list(self.garden_layout[0][2*i:2*i+2]+self.garden_layout[1][2*i:2*i+2])
        student_plants_full = []
        for j in range(len(student_plants)):
            student_plants_full.append(self.plant_dict[student_plants[j]])
        return student_plants_full

        
