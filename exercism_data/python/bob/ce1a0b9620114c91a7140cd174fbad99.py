def _say_nothing(message):
	return message.strip() == ''

def _is_yelling(message):
	return message.isupper()

def _is_question(message):
	return not _say_nothing(message) and message[-1] == '?'

class Bob(object):
	def hey(self, message):
		if _say_nothing(message):
			return 'Fine. Be that way!'
		if _is_yelling(message):
			return 'Woah, chill out!'
		if _is_question(message):
			return 'Sure.'
		return 'Whatever.'
