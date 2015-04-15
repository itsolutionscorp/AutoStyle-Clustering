from operator import itemgetter
'''
Given a coded diagram and optional list of student names, report the plants each student is responsible for
'''
class Garden:
	def __init__(self, plant_codes, students=\
			"Alice Bob Charlie David Eve Fred Ginny Harriet Ileana Joseph Kincaid Larry".split()):
		plant_codes = plant_codes.replace('\n','')
		ngardens = (len(plant_codes))//4	# each student has four plant_codes
		students = [students[ii] for ii in range(ngardens)]
		students.sort()
		self.gardendict = dict()
		for ii in range(ngardens):
			plant_inds = (ii*2, ii*2+1, 2*(ngardens)+ii*2, 2*(ngardens)+ii*2+1)
			self.gardendict[students[ii]] = list(itemgetter(*plant_inds)(plant_codes))
		
	def plants(self, student):
		plantdict = {"G":"Grass","V":"Violets","R":"Radishes","C":"Clover"}
		return [plantdict[plant] for plant in self.gardendict[student]]

if __name__ == '__main__':
	garden = Garden('VRGC\nCCGG')
	print(garden.plants("Alice"))
