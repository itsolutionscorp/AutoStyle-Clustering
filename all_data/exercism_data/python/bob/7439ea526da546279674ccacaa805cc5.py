def hey(what):

	def is_asking_question(s):
		last_char = s[-1:]
		return last_char == '?' and not is_yelling(s)

	def is_yelling(s):
		return s.isupper()

	def is_not_saying_anything(s):
		return s == '' or s.isspace()

	if is_asking_question(what):
		return 'Sure.'
	elif is_yelling(what):
		return 'Whoa, chill out!'
	elif is_not_saying_anything(what):
		return 'Fine. Be that way!'
	else:
		return 'Whatever.'
