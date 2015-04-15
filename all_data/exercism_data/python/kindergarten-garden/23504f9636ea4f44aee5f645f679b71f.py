
plants = {"V":"Violets", "C":"Clover", "G":"Grass", "R":"Radishes"}

class Garden:
    def __init__(self,diagram, **kwargs):
        if not kwargs:
            children = "Alice Bob Charlie David Eve Fred Ginny Harriet Ileana Joseph Kincaid Larry".split()
        else:
            children = sorted(kwargs["students"])
        self.planted = {child: [] for child in children}
        rows = diagram.split()
        for row in rows:
            for pos in range(len(row)):
                index = pos/2
                self.planted[children[index]].append(plants[row[pos]])

    def plants(self,child):
        return self.planted[child]

