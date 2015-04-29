import re
class Bob(object):
	def __init__(self):
		pass
	def hey(self,string):

		allUpper = re.compile(r'^[\[A-Z]\d\s]+$')
		digitAndPunc = re.compile(r'^[\d]+$')
		if string.strip() == "":
			return "Fine. Be that way!"

		elif digitAndPunc.match(''.join(e for e in string if e.isalnum())):
			if string[-1] == "?":
				return "Sure."
			else:
				return "Whatever."

		elif allUpper.match(''.join(e for e in string if e.isalnum())) or string.upper() == string:
			return 'Woah, chill out!'

		elif string[-1] == "?":
			return "Sure."

		else:
			return "Whatever."
