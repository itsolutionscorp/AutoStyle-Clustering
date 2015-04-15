#question ends with a ?
# yell ! & CAPS
# empty nothing/white spaces
# else: what, talking forcefully 

class Bob(object):
	'''This is a Bob class that does 3 things'''
	def __init__(self):
		self.__statement = "test"
	
	@property
	def statement(self):
		return self.__statement
	@statement.setter
	def statement(self, statement):
		self.__statement = statement

	def hey(self, statement):
		self.__statement = statement
		
		if (not (statement.isupper()):
			yell = False
		elif (statement.isupper() or '!' in statement):
			yell = True
		else:
			yell = False
		
		# test for question mark at the end of sentence
		if yell:
			print "Whoa, chill out!"
		elif '?' == statement[-1:]:
			print "Sure."
		# Test for white-spaces, tabs, and empty string
		elif statement.isspace() or statement == "":
			print "Fine. Be that way!"
		else:
			print "Whatever."

b = Bob
b.hey("awef")
