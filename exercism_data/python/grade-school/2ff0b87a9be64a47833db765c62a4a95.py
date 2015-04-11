class School(object):

	def __init__(self,school_name):
		self.s_name =school_name
		self.db={}
		#self.grade=self.db

	def add(self,name,grade):
		if grade in self.db.keys():
			self.db[grade].update({name})
		else:
			self.db[grade]={name}
		#print self.db
		return self.db

	def grade(self,grade):
		#print self.db[grade]
		if grade in self.db.keys():
			return self.db[grade]
		else:
			return set()

	def sort(self):
		temp={}
		for key in self.db.keys():
			temp[key]=tuple(self.db[key])
		return temp
