class Garden:
    def __init__(self, layout, students = ['Alice', 'Bob', 'Charlie', 'David',
                                           'Eve', 'Fred', 'Ginny', 'Harriet',
                                           'Ileana', 'Joseph', 'Kincaid', 'Larry']):
        self.layout = layout.replace('\n','')
        self.students = sorted(students)
        half = int(len(self.layout)/2)
        row1 = self.layout[:half]
        row2 = self.layout[half:]
        n = 0
        self.plants_dict = {}
        for child in self.students:
            self.plants_dict[child] = row1[n:n+2] + row2[n:n+2]
            n += 2

    def plants(self, student):
        ps = self.plants_dict[student]
        plants_list = []
        for p in ps:
            if p == "R":
                plants_list.append("Radishes")
            elif p == "C":
                plants_list.append("Clover")
            elif p == "G":
                plants_list.append("Grass")
            elif p == "V":
                plants_list.append("Violets")
        return plants_list
        
    
                 
