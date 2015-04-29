from collections import defaultdict

class Garden:
    STUDENTS = ['Alice',
                'Bob',
                'Charlie',
                'David',
                'Eve',
                'Fred',
                'Ginny',
                'Harriet',
                'Ileana',
                'Joseph',
                'Kincaid',
                'Larry']

    PLANTS = {'G': 'Grass',
              'C': 'Clover',
              'R': 'Radishes',
              'V': 'Violets'}

    def __init__(self, arrangement, students=STUDENTS):
        self.students = sorted(students)
        arrangement = arrangement.split('\n')
        top = [arrangement[0][x:x+2] for x in range(0, len(arrangement[0]),2)]
        bottom = [arrangement[1][x:x+2] for x in range(0, len(arrangement[1]),2)]
        plants = [reduce(lambda a, b: a+b, plants) for plants in map(None, top, bottom)]
        self.responsibilities = dict(map(None, self.students, plants))

    def plants(self, student):
        result = []
        for plant in self.responsibilities[student]:
            result.append(self.PLANTS[plant])
        return result
