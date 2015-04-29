
class Garden ():
    
    def __init__(self, garden, students = []):
        if len(students) > 0:
            self.students = dict(zip(sorted(students),range(len(students))))
        else:
            self.students = {"Alice":0, "Bob":1, "Charlie":2, "David":3, "Eve":4, "Fred":5, "Ginny":6, "Harriet":7, "Ileana":8, "Joseph":9, "Kincaid":10, "Larry":11}
        self.plants_avi = {'G':"Grass",'C': "Clover",'R': "Radishes",'V': "Violets"}
        self.garden = garden.split()
    
    def plants (self,who):
        pos = self.students[who]
        st_plants = []
        st_plants.append(self.plants_avi[self.garden[0][2*pos]])
        st_plants.append(self.plants_avi[self.garden[0][2*pos+1]])
        st_plants.append(self.plants_avi[self.garden[1][2*pos]])
        st_plants.append(self.plants_avi[self.garden[1][2*pos+1]])
        return st_plants
    
