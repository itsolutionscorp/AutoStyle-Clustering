# William Morris
# exercism.io
# garden.py

class Garden:

    def __init__(self, rows, students =['Alice', 'Bob', 'Charlie', 'David', 'Eve',
                                        'Fred', 'Ginny', 'Harriet', 'Ileana',
                                        'Joseph', 'Kincaid', 'Larry'] ):
        
        self.names = students
        self.names.sort()
        self.seeds ={'G':'Grass','C':'Clover','R':'Radishes','V':'Violets'}
        self.rows = rows.split()
        name_index = 0
        self.students_cups = {}
        for cups in range(0,len(self.rows[0]),2):
            cups_first_row = self.rows[0][cups:cups+2]
            cups_second_row = self.rows[1][cups:cups+2]
            self.students_cups[self.names[name_index]]=cups_first_row + cups_second_row
            name_index += 1
        self.students_plants = {}
        for name in self.students_cups.keys():
            self.students_plants[name] =  [self.seeds[seed] for seed in
                                          self.students_cups[name]]
            
    def plants(self,name):
        return self.students_plants[name]
    
