class Garden:
    def __init__(self,plantsingarden,students="Alice Bob Charlie David Eve Fred Ginny Harriet Ileana Joseph Kincaid Larry".split()):
        self.students = sorted(students)
        self.plants2d = plantsingarden.partition('\n')
        self.plantsrow1 = self.plants2d[0]
        self.plantsrow2 = self.plants2d[2]
    def plants(self, kid):
        def expand(pr12):
            plantlist = []
            for a in range(len(pr12)):
                if pr12[a] == "V":
                    plantlist.append("Violets")
                if pr12[a] == "R":
                    plantlist.append("Radishes")
                if pr12[a] == "G":
                    plantlist.append("Grass")
                if pr12[a] == "C":
                    plantlist.append("Clover")
            return plantlist
        for b in range(len(self.students)):     
            if kid == self.students[b]:
                plantr12 = self.plantsrow1[b*2:(b*2)+2] + self.plantsrow2[b*2:(b*2)+2]
        return expand(plantr12)
