
import re

_question = re.compile(".*\?$")
#_exclaim = re.compile(".*!$")
_digits = re.compile('\d')

class Bob ():
	def is_number(self, x):
		try:
			float(x)
			return True
		except:
			return False
			
	def is_shouting(self, input):
		if len(input.strip("?")) > 1:
			if self.contains_only_numbers(input) != True:
				return input.upper() == input	
	
	def contains_only_numbers(self, d):
		splitter = " "
		if d.find(",") != -1:
			splitter = ","
		list_d = [x for x in d.split(splitter) if not self.is_number(x)]
		# print list_d
		if len (list_d) > 0:
			return False
		return True
				
	def hey(self, input):
		out = None
		if len(input.strip()) == 0:
			out = "Fine. Be that way!"	
		elif self.is_shouting(input):
			out = "Woah, chill out!"
		
		if not out:
			if _question.findall(input):
				out = "Sure."
			else:
				out = "Whatever."
			
		return out
