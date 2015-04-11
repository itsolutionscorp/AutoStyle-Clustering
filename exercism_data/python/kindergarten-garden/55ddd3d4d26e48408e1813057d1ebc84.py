#!/usr/bin/python
class Garden():
    def __init__(self, garden, students = None):
        if students is None:
            self.students = "Alice Bob Charlie David Eve Fred Ginny Harriet Ileana Joseph Kincaid Larry".split()
        else:
            self.students = sorted(students)
        split_garden = lambda g: ["".join(z) for z in zip(*[[row[x:x+2] for x in range(0, len(row), 2)] for row in g.split("\n")])]
        self.garden_mapping = dict(zip(self.students, split_garden(garden)))
        flowers = ['Grass', 'Clover', 'Radishes', 'Violets']
        self.flower_mapping = dict(zip([f[0] for f in flowers], flowers))

    def plants(self, student):
        return [self.flower_mapping[f] for f in self.garden_mapping[student]]
