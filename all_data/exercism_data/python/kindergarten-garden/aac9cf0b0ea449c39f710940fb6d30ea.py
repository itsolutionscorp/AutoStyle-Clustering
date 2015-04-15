#-------------------------------------------------------------------------------
# Name:        McFace3000
# Purpose:      Project Maximus!
#-------------------------------------------------------------------------------
class Garden(object):
    def __init__(self, garden, students = []):
        if (len(students) == 0):
            students = [
                        "Alice","Bob","Charlie","David","Eve","Fred","Ginny",
                        "Harriet","Ileana","Joseph","Kincaid","Larry"
                        ]
            dict_of_students = {
                            "Alice":"","Bob":"","Charlie":"","David":"","Eve":"",
                            "Fred":"","Ginny":"","Harriet":"","Joseph":"",
                            "Kincaid":"","Larry":""
                            }
        else:
            dict_of_students = {}
            for x in students:
                dict_of_students[x] = ""
        self.dict_of_students = dict_of_students
        students = sorted(students)
        self.students = students
        self.row1 = garden[0:garden.find("\n")]
        self.row2 = garden[garden.find("\n")+1:]

    def plants(self, n):
        plants = {
            "V": "Violets", "C":"Clover", "R":"Radishes", "G": "Grass"
            }
        e_row1 = [plants[x] for x in self.row1]
        e_row2 = [plants[x] for x in self.row2]
        start = 0
        student_name = self.students[self.students.index(n)]
        for x in self.students:
            self.dict_of_students[x] = e_row1[start:start+2] + e_row2[start:start+2]
            start+=2
        return self.dict_of_students[n]
