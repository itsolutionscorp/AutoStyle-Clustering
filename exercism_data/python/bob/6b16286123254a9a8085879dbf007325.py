class Statement:
	SILENCE  = 0
	SHOUTING = 1
	QUESTION = 2
	OTHER    = 3
	
	@staticmethod
	def type(value):
		s = Statement._trim(value)
		if Statement._is_silence(s):
			return Statement.SILENCE
		elif Statement._is_shouting(s):
			return Statement.SHOUTING
		elif Statement._is_question(s):
			return Statement.QUESTION
		else:
			return Statement.OTHER
	
	@staticmethod
	def _trim(s):
		if (s == None):
			return ''
		else:
			return s.strip()
			
	@staticmethod
	def _is_silence(s):
		return (s == '')
	
	@staticmethod
	def _is_shouting(s):
		return (s == s.upper())
		
	@staticmethod
	def _is_question(s):
		return s.endswith('?')
    
class Bob:
	ANSWERS = { Statement.SILENCE : 'Fine. Be that way.',
				Statement.SHOUTING: 'Woah, chill out!',
				Statement.QUESTION: 'Sure.',
				Statement.OTHER   : 'Whatever.' }

	def hey(self, s):
		return Bob.ANSWERS[Statement.type(s)]
