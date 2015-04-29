class Garden(object):
    def __init__(self, plantinit, students = 'Alice Bob Charlie David Eve Fred Ginny Harriet Ileana Joseph Kincaid Larry'.split()
):
        self.planta = []
        self.plantb = []
        self.plantc = []
        self.plantd = []
        for i in range(len(plantinit)):
            if i < len(plantinit) / 2:
                if i == 0 or i % 2 == 0:
                    self.planta.append(plantinit[i])
                else:
                    self.plantb.append(plantinit[i])
            if i > len(plantinit) / 2:
                if i % 2 == 0:
                    self.plantd.append(plantinit[i])
                else:
                    self.plantc.append(plantinit[i])
        self.students = sorted(students)
        
    def plants(self, student):
        planttypes = 'Clover Grass Radishes Violets'.split()
        plantletters = 'C G R V'.split()
        output = []
        for i in range(len(self.students)):
            if student == self.students[i]:
                output = [self.planta[i], self.plantb[i], self.plantc[i], self.plantd[i]]
        for i in range(len(output)):
            for j in range(len(plantletters)):
                if output[i] == plantletters[j]:
                    output[i] = planttypes[j]
        return output

                
        
        
