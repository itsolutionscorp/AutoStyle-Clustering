class Garden(object):
    def __init__(self, diagram, students='Alice Bob Charlie David Eve Fred Ginny Harriet Ileana Joseph Kincaid Larry'.split()):
        self.diagram = diagram.split()
        self.students = sorted(students)
    
    def plants(self, name):
        # create plants dictionary
        plants = { 'G': 'Grass', 'C': 'Clover', 'R': 'Radishes', 'V': 'Violets' }
        # create nested array of plant rows
        row = []
        row.append(list(self.diagram[0]))
        row.append(list(self.diagram[1]))
        # initialize empty 'student: plants' dictionary
        student_plants = dict()

        # loop through the number of students that planted seeds
        for i in range(len(row[0]) / 2):
            # create a list of 4 plants that belong to that student    
            plants_list = []
            for j in range(2):
                plants_list.append(plants[row[j][i * 2]])
                plants_list.append(plants[row[j][i * 2 + 1]])
            
            # add the plant list to the dictionary of plants keyed off students
            student_plants[self.students[i]] = plants_list
        return student_plants[name]
