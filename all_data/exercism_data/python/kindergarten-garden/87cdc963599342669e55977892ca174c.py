class Garden:
    def __init__(self, gardenkey,**kwargs):
        self.plantkey = {'R': "Radishes",
                    'C': "Clover",
                    'G': "Grass",
                    'V': "Violets"
        }
        
        if not kwargs:
            self.kidkey = ['Alice', 'Bob', 'Charlie',
                'David', 'Eve', 'Fred', 'Ginny', 'Harriet',
                'Ileana', 'Joseph', 'Kincaid', 'Larry']
				
        else:
			print kwargs["students"]
			self.kidkey = sorted(kwargs["students"])
        
        
        print self.kidkey
        self.gardenkey = gardenkey
        
        
        
    def plants(self,student):
        id = self.kidkey.index(student)*2
        
        gardenkey = self.gardenkey.split()
        output = []
        
        for rows in range(2):
            for cols in range(2):
                output.append(self.plantkey[gardenkey[rows][id+cols]])
        return output
