class Garden(object):

    def __init__(self, plants, students=["Alice", "Bob", "Charlie", "David",
                                         "Eve", "Fred", "Ginny", "Harriet",
                                         "Ileana", "Joseph", "Kincaid",
                                         "Larry"]):
        self.plants_list = plants.splitlines()
        self.students = sorted(students)
        self.plants_map = {
            'V': "Violets", 'R': "Radishes", 'C': "Clover", 'G': "Grass"
        }

    def plants(self, student):
        patch = self.students.index(student)
        plants_list = self.plants_list[0][patch * 2:(patch + 1) * 2]
        plants_list += self.plants_list[1][patch * 2:(patch + 1) * 2]
        return self.map_plants(plants_list)

    def map_plants(self, plants):
        ret = []
        for k in plants:
            ret.append(self.plants_map[k])
        return ret
