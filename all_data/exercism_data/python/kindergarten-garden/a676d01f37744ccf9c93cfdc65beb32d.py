plants_dict = {'V':"Violets", 'R':"Radishes", 'G':"Grass", 'C':"Clover"}
default_students = ["Alice", "Bob", "Charlie", "David",
                    "Eve", "Fred", "Ginny", "Harriet",
                    "Ileana", "Joseph", "Kincaid", "Larry"]

class Garden(object):
        
    def __init__(self, garden, **kargs):
        self.garden = garden.split()
        if kargs:
            self.students = sorted(kargs['students'])
        else:
            self.students = default_students

    def plants(self, student):
        if student in self.students:
            studentplace = self.students.index(student)*2
            student_plants = self.garden[0][studentplace]+self.garden[0][studentplace+1]\
                             +self.garden[1][studentplace]+self.garden[1][studentplace+1]
            return [plants_dict[plant] for plant in student_plants]
        else:
            raise ValueError("Student not found in class")
