PLANTS = dict(zip("G C R V".split(),
                  "Grass Clover Radishes Violets".split()))

STUDENTS = ("Alice Bob Charlie David "
            "Eve Fred Ginny Harriet "
            "Ileana Joseph Kincaid Larry").split()

class Garden:
    def __init__(self, seeds, students = None):
        self.students = students and sorted(students) or STUDENTS
        self.seeds = seeds.split()
    
    def plants(self, kid):
        if not kid in self.students:
            raise ValueError("Unknown kid")
        
        idx = self.students.index(kid)*2
        owned_seeds = "".join(row[idx:idx+2] for row in self.seeds)
        return [PLANTS[s] for s in owned_seeds]
