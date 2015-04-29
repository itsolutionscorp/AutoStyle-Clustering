class Garden():
    plants_mapping = {'G' : 'Grass', 'C' : 'Clover', 'R' : 'Radishes', 'V' : 'Violets'}
    def __init__(self, rows, students = ['Alice', 'Bob', 'Charlie', 'David', 'Eve', 'Fred', 'Ginny', 'Harriet', 'Ileana', 'Joseph', 'Kincaid', 'Larry']):           
        self.students = sorted(students)
        self.rows = rows.split()
        self.cups = []
        for row in self.rows:
            row =  [row[i:i+2] for i in range(0, len(row), 2)]
            self.cups.append(row)            
		
    def plants(self, student):        
        plantlist = []
        position = self.students.index(student)
        cups = self.cups[0][position] + self.cups[1][position]
        for cup in cups: plantlist.append(Garden.plants_mapping[cup])
        return plantlist
