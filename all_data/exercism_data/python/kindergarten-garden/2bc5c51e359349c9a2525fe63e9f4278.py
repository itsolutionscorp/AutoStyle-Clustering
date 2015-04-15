class Garden(object):
    vegetables = {'G': 'Grass', 'C': 'Clover', 'R':'Radishes', 'V':'Violets'}

    def __init__(self, layout, students="Alice Bob Charlie David Eve Fred Ginny \
                                         Harriet Ileana Joseph Kincaid Larry".split()):
        self.layout = layout.split("\n")
        self.students = sorted(students)
        self.dict = {name: self.layout[0][num*2:num*2+2] + self.layout[1][num*2:num*2+2] 
                     for num, name in enumerate(self.students)}

    def plants(self, name):
        return [Garden.vegetables[letter] for letter in self.dict[name]]
