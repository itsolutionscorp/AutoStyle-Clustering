class Garden(object):

    plantnames = {'C': 'Clover', 'V': 'Violets', 'G':'Grass', 'R':'Radishes'}


    def __init__(self, gardenstring, students = None):
        if students is None:
            self.children = "Alice, Bob, Charlie, David, " \
                       "Eve, Fred, Ginny, Harriet, " \
                       "Ileana, Joseph, Kincaid, Larry".split(", ")
        else:
            self.children = students
            self.children.sort()
        self.plantrows = gardenstring.splitlines()

    def getplants(self, name):
        return ''.join([row[self.children.index(name)*2:self.children.index(name)*2+2] for row in self.plantrows])

    def plants(self, name):
        return [self.plantnames[plant] for plant in self.getplants(name)]
