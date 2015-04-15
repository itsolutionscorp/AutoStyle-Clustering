'''
Bob listens to a sentence
and replies accordingly.
'''
class Bob:
	def hey(self, sentence):
		# if sentence is empty or whitespace
		if not sentence.strip():
			return 'Fine. Be that way!'
		# if sentence is all uppercase
		if sentence == sentence.upper() and sentence.upper() != sentence.lower():
			return 'Woah, chill out!'
		# if sentence ends with ?
		if sentence[len(sentence)-1] == '?':
			return 'Sure.'
		# else return default
		return 'Whatever.'
