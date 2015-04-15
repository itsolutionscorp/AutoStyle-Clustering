class Garden:
    students_default = sorted("Alice Bob Charlie David Eve Fred Ginny Harriet Ileana Joseph Kincaid Larry".split())
    flowers = dict((p[0], p) for p in "Clover Grass Radishes Violets".split())

    def __init__(self, flowers, students=None):
        self.students = students and sorted(students) or self.__class__.students_default
        self.flowers = flowers

    def plants(self, student):
        index = self.students.index(student) * 2
        flowers = "".join( f[index:index+2] for f in self.flowers.split("\n") )
        return [self.__class__.flowers[f] for f in flowers]
