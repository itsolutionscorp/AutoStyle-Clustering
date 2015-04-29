def is_silent(phrase):
	return (phrase == None) or (phrase.strip() == '')

def is_shouting(phrase):
	return (not is_silent(phrase) and (phrase == phrase.upper()))

def is_question(phrase):
	return (not is_silent(phrase) and phrase.endswith('?'))

def is_other(phrase):
	return True

class Bob:
	Responses = [[is_silent,   'Fine. Be that way.'],
		  	  	 [is_shouting, 'Woah, chill out!'],
				 [is_question, 'Sure.'],
				 [is_other,    'Whatever.']]

	def hey(self, s):
		for [f,r] in self.Responses:
			if f(s):
				return r
