#
#
#

class Garden:
    # Constructor
    def __init__(self,garden,students = None):
        self.garden = garden.replace("\n","")
        self.students = students
        if students is not None:
            self.students.sort()

    def plants(self,name):
        if self.students is None:
            # child position determined with alphabet
            alphabet = 'abcdefghijklmnopqrstuvwxyz'
            pos = 2*(alphabet.index(name[0].lower()))
        else:
            # child position determined with class property list
            self.students
            pos = 2*(self.students.index(name))
        plant_names = {'V':'Violets','R':'Radishes','G':'Grass','C':'Clover'}
        # Length of garden row
        row_len = len(self.garden)/2
        # Construct output list
        plants = []
        plants.append(plant_names[self.garden[pos]])
        plants.append(plant_names[self.garden[pos+1]])
        plants.append(plant_names[self.garden[pos+row_len]])
        plants.append(plant_names[self.garden[pos+1+row_len]])
        return plants

