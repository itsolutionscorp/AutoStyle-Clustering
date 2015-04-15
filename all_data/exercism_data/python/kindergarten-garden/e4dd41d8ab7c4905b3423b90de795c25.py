class Garden:
	_keys = {"G":"Grass",
			 "C":"Clover",
			 "R":"Radishes",
			 "V":"Violets" }

	def __init__(self,values,students = None):
		self._kids = students or ["Alice","Bob","Charlie","David","Eve","Fred","Ginny","Harriet","Ileana","Joseph","Kincaid","Larry"]
		self._kids.sort()
		self._assignments = self.assignments(values)

	def plants(self,name):
		return self._assignments[name]
		
	def assignments(self, displacement):
		self._assignments = {}
		displacement = displacement.strip()
		for word in displacement.split("\n"):
			for index in range(len(word)):
				kid = int(index/2)
				if self._kids[kid] in self._assignments.keys():
					self._assignments[self._kids[kid]] += [self._keys[word[index]]]
				else:
					self._assignments[self._kids[kid]] = [self._keys[word[index]]]
		return self._assignments
