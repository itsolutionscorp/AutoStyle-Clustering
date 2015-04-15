#
# Skeleton file for the Python "Bob" exercise.
#
class Bob(object):
	def hey(self, sentence):
		if not sentence.strip():
			return 'Fine. Be that way!'
		elif sentence.isupper():
			return  'Whoa, chill out!'
		elif sentence.endswith('?'):
			return 'Sure.'
		return 'Whatever.'
