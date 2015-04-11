default_students = ["Alice","Bob",    "Charlie","David", "Eve",    "Fred",
                    "Ginny","Harriet","Ileana", "Joseph","Kincaid","Larry"]

p_to_plant = { 'C':"Clover", 'G':"Grass", 'R':"Radishes", 'V':"Violets" }

class Garden:
    def __init__(self, plants, students=default_students):
        self.students = sorted(students)
        self.plants_ = plants.split()

    def plants(self, student):
        pindex = self.students.index(student)*2
        s_plants = [self.plants_[0][pindex], self.plants_[0][pindex+1],
                    self.plants_[1][pindex], self.plants_[1][pindex+1]]
        return [p_to_plant[p] for p in s_plants]
