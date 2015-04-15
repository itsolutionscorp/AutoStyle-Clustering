def hey(speech):
	if _is_silence(speech):
		return 'Fine. Be that way!'
	elif _is_shouting(speech):
		return 'Woah, chill out!'
	elif _is_question(speech):
		return 'Sure.'
	else:
		return 'Whatever.'


def _is_silence(speech):
	return speech.strip() == ''

def _is_question(speech):
	return speech.endswith('?')

def _is_shouting(speech):
	return speech.isupper()
