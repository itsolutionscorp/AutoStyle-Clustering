class Garden():

	def __init__(self,rows,students='Alice Bob Charlie David Eve Fred Ginny Harriet Ileana Joseph Kincaid Larry'.split()):
		rows = rows.split()
		students.sort()
		kinderen = students[0:len(rows[0])/2]
		self.kindpflanze = {}
		for i in range(0,len(kinderen)):
			self.kindpflanze[kinderen[i]]=rows[0][i*2:i*2+2]+rows[1][i*2:i*2+2]
		
		self.plantdict={'V':'Violets', 'C':'Clover', 'R':'Radishes','G':'Grass'} 
			
	def plants(self,kind):
		pflanznummer = self.kindpflanze[kind]
		out =[]
		for i in pflanznummer:
			out.append(self.plantdict[i])
		return out
