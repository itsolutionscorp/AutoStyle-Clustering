def is_silent(phrase):
	return (phrase == None) or (phrase.strip() == '')

def is_shouting(phrase):
	return (not is_silent(phrase) and (phrase == phrase.upper()))

def is_question(phrase):
	return (not is_silent(phrase) and phrase.endswith('?'))

class Bob:
	Responses = [[is_silent,   'Fine. Be that way.'],
		  	  	 [is_shouting, 'Woah, chill out!'],
				 [is_question, 'Sure.']]

	def hey(self, s):
		answer = 'Whatever.'
		for [f,r] in self.Responses:
			if f(s):
				answer = r
				break
		return answer
