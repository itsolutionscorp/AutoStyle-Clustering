# Kindergarten Garden

class Garden():
    """Represents a kindergarten garden.
    Accepts Violets, Clover, Grass, Radish, in a 2-lines diagram"""

    def __init__(self,diagram,students=None):
        if students:
            students = sorted(students)  # alphabetical sort
        else:  # revert to default
            students = "Alice Bob Charlie David Eve Fred Ginny Harriet Ileana Joseph Kincaid Larry".split()
        plantsnames = dict(zip("VRCG",
                               "Violets Radishes Clover Grass".split() ))
        # prepare a dict of plants for each kid
        diagram = diagram.splitlines()
        self.plantsperstudent = dict.fromkeys(students)
        studentnumber = 0
        while diagram[0]:  # as long as the diagram is not consumed
            student = students[studentnumber]
            plants = diagram[0][:2]+diagram[1][:2]  # letters from the 2 rows
            plants = list( plantsnames[l] for l in plants )  # translate
            self.plantsperstudent[student] = plants
            # consume diagram and increment in list of students
            diagram = [diagram[0][2:],diagram[1][2:]]
            studentnumber += 1




    def plants(self,kid):
        """returns name of plants for this kid"""
        return self.plantsperstudent[kid]
