kids = ["Alice", "Bob","Charlie", "David",
	"Eve", "Fred", "Ginny", "Harriet",
	"Ileana", "Joseph", "Kincaid", "Larry"] # already sorted

plants = ["Violets", "Radishes", "Clover","Grass" ]
plant_mapping = dict((plant[0],plant) for plant in plants )

class Garden:

    def __init__(self, diag, students = kids):
	self._kids = sorted(students)
	u,d = diag.split('\n')	
	self._diag = [ ''.join( (u[i:i+2],d[i:i+2]) ) for i in range(0,len(diag)/2+1-2,2) ]

    def plants(self, kid):
	return [ plant_mapping[p] for p in self._diag[self._kids.index(kid)] ]
