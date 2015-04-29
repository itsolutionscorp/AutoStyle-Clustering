import string

class Garden():

    def __init__(self,garstr, students=None):

        if students == None:
            self.students = ['Alice', 'Bob', 'Charlie', 'David', 'Eve', 'Fred', 'Ginny', 
                        'Harriet', 'Ileana', 'Joseph', 'Kincaid', 'Larry']
        else:
            self.students = sorted(students) 

        try:
            self.rows = garstr.split('\n')
        except AttributeError:
            self.rows = gartstr
        self.alph = string.ascii_lowercase

        self.split_plants = []

        for row in self.rows:
            self.split_plants += [row[i:i+2] for i in range(0, len(row), 2)]

    def plants(self,kid):
        
        kids_plants = []
        final_list = []

        plant_dict = {'V' : 'Violets',
                      'R' : 'Radishes',
                      'C' : 'Clover',
                      'G' : 'Grass'
                      }

        for index, child in enumerate(self.students):
            if child == kid:
                kids_plants.append(self.split_plants[index])
                kids_plants.append(self.split_plants[index + int(len(self.split_plants)/2)])

        for plants in kids_plants:
            for char in plants:
               final_list.append(plant_dict[char]) 

        return final_list
