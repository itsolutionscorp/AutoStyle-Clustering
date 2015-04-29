from collections import defaultdict

class Garden():
    _plants = dict(zip('G C R V'.split(), 'Grass Clover Radishes Violets'.split()))
    
    def __init__(self, plants, students=['Alice',  'Bob',    'Charlie', 'David', 
                                       'Eve',    'Fred',   'Ginny',   'Harriet',
                                       'Ileana', 'Joseph', 'Kincaid', 'Larry']):
        students = sorted(students)
        self.garden = defaultdict(list)
        for cups in plants.split():
            for i, student in zip(range(len(cups)//2), students):
                for plant in cups[i*2 : i*2+2]:
                    if plant in Garden._plants:
                        self.garden[student].append(Garden._plants[plant])  
        
    
    def plants(self, student):
        return self.garden[student]
