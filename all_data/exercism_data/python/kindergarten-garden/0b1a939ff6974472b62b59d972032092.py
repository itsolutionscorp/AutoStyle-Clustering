import re

class Garden:
	Students = []
	Line1 = "" 
	Line2 = ""
	def __init__(self,GardenRaw, students = ''):
		Garden = re.sub(r'\W+', '',GardenRaw)
		self.Line1, self.Line2  = Garden[:len(Garden)/2], Garden[len(Garden)/2:]
		if students != '':
			for student in students:
				self.Students.append(student.lower()[0])
			self.Students.sort()
		else:
			self.Students = list(map(chr, range(ord('a'), ord('l')+1)))
	def plants(self,name):
		num = self.getstudentnum(name)
		return self.getplantsonline(self.Line1, num) + self.getplantsonline(self.Line2, num)
	def getstudentnum(self, name):
		count = 1
		for student in self.Students:
			if student == name.lower()[0]:
				return count
			count += 1
	def getplantsonline(self, line, num):
		output = []
		count = 1
		for letter in line:
			if count == (num*2) or count == (num*2-1):
				output.append(self.plantname(letter))
			count += 1
		return output
	def plantname(self,letter):
		return {
			'G' : 'Grass', 
			'C' : 'Clover', 
			'R' : 'Radishes', 
			'V' : 'Violets',
			}[letter]
