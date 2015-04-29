plant_abbr = {'G': 'Grass', 'C': 'Clover', 'R': 'Radishes', 'V': 'Violets'} 

class Garden():
    def __init__(self, plants, students=None):
        self.plant_list = plants.split()
        self.kids_plants = {}
 
        if students == None:
            i = 0
            for c in 'ABCDEFGHIJKL':
               if i < len(self.plant_list[0]):
                   self.kids_plants[c] = (self.plant_list[0][i] +
                                          self.plant_list[0][i+1] +
                                          self.plant_list[1][i] +
                                          self.plant_list[1][i+1])
                   i += 2
        else:
            i = 0
            students.sort()
            for s in students:
                if i < len(self.plant_list[0]):
                   self.kids_plants[s[0]] = (self.plant_list[0][i] +
                                             self.plant_list[0][i+1] +
                                             self.plant_list[1][i] +
                                             self.plant_list[1][i+1])
                   i += 2


    def plants(self, student):
        plant_list = []
        for plant in self.kids_plants[student[0]]:
            plant_list.append(plant_abbr[plant])
        
        return plant_list
