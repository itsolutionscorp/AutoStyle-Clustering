#
# Skeleton file for the Python "Bob" exercise.
#
class Question(object):
	def __init__(self,question):
		self.question = question.strip() #strip whitespace
	def is_question(self):
		#Questions end with a question mark
		if self.question[-1] == '?':
			return True
	def is_yelling(self):
		#Yelling is in all caps
		if self.question.isupper():
			return True
	def is_nothing(self):
		#No question is a string with a length of zero
		if not len(self.question):
			return True

def hey(what):

	question = Question(what)
	response = ""

	if question.is_nothing():
		response = "Fine. Be that way!"
	elif question.is_yelling():
		response = "Whoa, chill out!"
	elif question.is_question():
		response = "Sure."
	else:
		response = "Whatever."

	return response
