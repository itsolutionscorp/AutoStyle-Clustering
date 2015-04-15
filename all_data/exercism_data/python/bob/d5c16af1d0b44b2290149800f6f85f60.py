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

	def hey(self, statement):
		answer = 'Whatever.'
		for [test, response] in self.Responses:
			if test(statement):
				answer = response
				break
		return answer
