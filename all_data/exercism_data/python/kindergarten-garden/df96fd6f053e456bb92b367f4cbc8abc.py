default_students = "Alice Bob Charlie David Eve Fred Ginny Harriet Ileana Joseph Kincaid Larry".split()
plant_names = {
    "C": "Clover",
    "G": "Grass",
    "R": "Radishes",
    "V": "Violets"
}

class Garden(object):
    def __init__(self, garden, students=default_students):
        self.student_lookup = { s: i for i, s in enumerate(sorted(students)) }
        self.lines = garden.split("\n")

    def plants(self, student):
        index = self.student_lookup[student] * 2
        plants = "".join( line[index:index+2] for line in self.lines )
        return [ plant_names[p] for p in plants ]
