class School(object):
	def __init__(self, name, pw = "trusted"):
		self.pw = pw
		self.name = name
		self.db = {}
		self.lets_go()
		
	
	def add(self, student, grade):
		new = self.db.get(grade, set())
		new.update({student})
		self.db[grade] = new
			
	def grade(self,num):
		return self.db.get(num, set())
		
	def sort(self):
		for key in self.db:
			ordered = sorted(self.db[key])
			self.db[key] = tuple(ordered)
		return self.db


	
	def add_student(self):
		s = raw_input("Student Name: ")
		g = raw_input("Grade: ")
		self.add(s, int(g))
		print "Press 'a' to add another student."
		print "Press any other key to return to the main menu."
		query = raw_input("> ")
		if query.lower() == 'a':
			self.add_student()
		else:
			return
	
	def verify(self):
		for i in range(3,0,-1):
			print "Please enter password. %d attempts remaining. Type 'Exit' to return to main menu" % i
			x = raw_input('> ')
			if x == "password":
				self.add_student()
				return
			if x.lower() == 'exit':
				return
		print "Please contact your SysAdmin or try again later."
		print "Goodbye."
		quit() 
			
	
	def lets_go(self):
		if self.pw == "trusted":
			return
		while True:
			print "To add a student to a grade, type 'ADD' at the prompt."
			print "To get all the students in a grade, type 'GRADE' at the prompt."
			print "To get all the students in the school, type 'SCHOOL' at the prompt."
			print "To exit this program, type 'EXIT' at the prompt."
			prompt = raw_input("> ").lower()
			if prompt == 'exit':
				return
			if prompt == 'school':
				print self.sort()
			elif prompt == 'grade':
				print "For which grade would you like to see the students?"
				grade = raw_input("> ")
				cur_grade = self.grade(int(grade))
				for s in cur_grade:
					print s,
				print '...'
			elif prompt == 'add':
				self.verify()
			else:
				print "I don't understand..."
				print "..."
