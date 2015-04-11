#
# Skeleton file for the Python "Bob" exercise.
#
class Bob(object):
	def hey(self, sentance):
		if sentance is None or sentance.strip() == "":
			return 'Fine. Be that way!'
		elif sentance.isupper():
			return  'Whoa, chill out!'
		elif sentance.endswith('?'):
			return 'Sure.'
		return 'Whatever.'
