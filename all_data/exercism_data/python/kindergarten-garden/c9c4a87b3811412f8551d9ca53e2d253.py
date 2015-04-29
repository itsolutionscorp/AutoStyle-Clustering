class Garden(object):

    CHILDREN = {'Alice': 0,
                'Bob': 2,
                'Charlie': 4,
                'David': 6,
                'Eve': 8,
                'Fred': 10,
                'Ginny': 12,
                'Harriet': 14,
                'Ileana': 16,
                'Joseph': 18,
                'Kincaid': 20,
                'Larry': 22}

    PLANTS = {'G': "Grass",
              'C': "Clover",
              'R': "Radishes",
              'V': "Violets"}

    def __init__(self, garden_layout, students = None):
        self.garden_layout = garden_layout.split()
        if students == None:
            self.students = Garden.CHILDREN
        else:
            self.students = {}
            students.sort()
            for i in range(len(students)):
                self.students[students[i]] = i*2

    def plants(self, child):
        plants = []
        for i in range(2):
            plants.append(Garden.PLANTS[self.garden_layout[i][self.students[child]]])
            plants.append(Garden.PLANTS[self.garden_layout[i][self.students[child]+1]])
        return plants
