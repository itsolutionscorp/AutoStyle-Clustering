
class Garden:

    plants_dic = {'V':'Violets','C':'Clover','R':'Radishes','G':'Grass'}


    def __init__(self,planted,students="Alice Bob Charlie David Eve Fred Ginny Harriet Ileana Joseph Kincaid Larry".split()):
        self.planted = planted.rstrip().split('\n')
        self.students = sorted(students)

    def plants(self,selected_student):
        self.selected_student = selected_student
        student_pots = []
        student_pots_full = []
        
        for chunk in self.planted:
#            student_pots.append(chunk[(self.students.index(self.selected_student)*2)])
            student_pots.append(chunk[((self.students.index(self.selected_student)*2)+0)])
            student_pots.append(chunk[((self.students.index(self.selected_student)*2)+1)])
        for entry in student_pots:
            student_pots_full.append(self.plants_dic.get(entry))
        return student_pots_full

