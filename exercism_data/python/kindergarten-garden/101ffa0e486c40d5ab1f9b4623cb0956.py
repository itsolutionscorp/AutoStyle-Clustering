from operator import itemgetter

class Garden:
	def __init__(self, plants, students=\
		"Alice Bob Charlie David Eve Fred Ginny Harriet Ileana Joseph Kincaid Larry".split()):
	
		ngardens = (len(plants)-1)//4
		students = [students[ii] for ii in range(ngardens)]
		students.sort()
		self.gardendict = dict()
		for ii in range(ngardens):
			plant_inds = (ii*2, ii*2+1, 1+2*(ngardens)+ii*2, 1+2*(ngardens)+ii*2+1)
			self.gardendict[students[ii]] = list(itemgetter(*plant_inds)(plants))
		return
		
	def plants(self, student):
		plantdict = {"G":"Grass","V":"Violets","R":"Radishes","C":"Clover"}
		return [plantdict[plant] for plant in self.gardendict[student]]

if __name__ == '__main__':
	garden = Garden('VRGC\nCCGG')
	print(garden.plants("Alice"))
