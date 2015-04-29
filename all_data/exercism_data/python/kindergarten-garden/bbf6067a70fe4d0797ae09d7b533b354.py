class Garden(object):
    
    PLANTS = {"G":"Grass","C":"Clover","R":"Radishes",
    "V":"Violets"}
    
    default_list = ["Alice","Bob","Charlie","David" ,"Eve",
    "Fred","Ginny","Harriet","Ileana","Joseph","Kincaid","Larry"]
    
    def __init__(self,diagram,students=default_list):
        self.diagram = diagram
        self.student_dict = self.build_dic(students)
        
    def build_dic(self,students):
        students.sort()
        student_dict={}
        index_count=2
        for name in students:
            student_dict[name]=range(index_count-2,index_count)
            index_count += 2
        return student_dict
    
    def plants(self,student_name):
        
        plants_list = []
        for row in self.diagram.split("\n"):
            for index in self.student_dict[student_name]:
                plants_list.append(Garden.PLANTS[row[index]])
        return plants_list
        
