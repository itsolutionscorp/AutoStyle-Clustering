SILENCE  = 0
SHOUTING = 1
QUESTION = 2
OTHER    = 3
	
def type_of(statement):
	if is_silence(statement):
		return SILENCE
	elif is_shouting(statement):
		return SHOUTING
	elif is_question(statement):
		return QUESTION
	else:
		return OTHER
		
def is_silence(s):
	return (s == None) or (s.strip() == '')

def is_shouting(s):
	return (s != None) and (s == s.upper())
	
def is_question(s):
	return (s != None) and s.endswith('?')
    
class Bob:
	RESPONDS_TO = { SILENCE : 'Fine. Be that way.',
			    	SHOUTING: 'Woah, chill out!',
					QUESTION: 'Sure.',
					OTHER   : 'Whatever.' }

	def hey(self, statement):
		return Bob.RESPONDS_TO[type_of(statement)]
