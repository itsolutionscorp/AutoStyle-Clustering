def is_silent(phrase):
	return (phrase is None) or not phrase.strip()

def is_shouting(phrase):
	return (phrase is not None) and phrase.isupper()

def is_question(phrase):
	return (phrase is not None) and phrase.endswith('?')

def is_anything_else(phrase):
	return True

class Bob:
	responses = [(is_silent,        'Fine. Be that way.'),
		  	  	 (is_shouting,      'Woah, chill out!'),
				 (is_question,      'Sure.'),
				 (is_anything_else, 'Whatever.')]

	def hey(self, statement):
		for test, response in self.responses:
			if test(statement):
				return response
