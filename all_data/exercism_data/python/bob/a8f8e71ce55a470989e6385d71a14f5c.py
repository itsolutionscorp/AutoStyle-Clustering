def is_silent(phrase):
	return (phrase is None) or not phrase.strip()

def is_shouting(phrase):
	return (not phrase is None) and phrase.isupper()

def is_question(phrase):
	return (not phrase is None) and phrase.endswith('?')

class Bob:
	responses = [(is_silent,   'Fine. Be that way.'),
		  	  	 (is_shouting, 'Woah, chill out!'),
				 (is_question, 'Sure.')]

	def hey(self, statement):
		for test, response in self.responses:
			if test(statement):
				return response
		return 'Whatever.'
