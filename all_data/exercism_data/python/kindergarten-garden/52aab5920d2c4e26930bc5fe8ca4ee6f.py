class Garden():
    refs = {'C': 'Clover',
            'G': 'Grass',
            'V': 'Violets',
            'R': 'Radishes'}

    def __init__(self, code, students = None):
        if not students:
            self.students = "Alice Bob Charlie David".split()+\
                            "Eve Fred Ginny Harriet".split()+\
                            "Ileana Joseph Kincaid Larry".split()
        else:
            self.students = sorted(students)

        rows = code.split("\n")

        self.plants_of = {}

        for i, student in enumerate(self.students):
            plant_initials = [list(rows[r][i*2:(i+1)*2]) for r in [0,1]]
            plant_initials = plant_initials[0] + plant_initials[1]
            
            v = [self.refs[p] for p in plant_initials]

            self.plants_of[student] = v

    def plants(self, student):
        return self.plants_of[student]
