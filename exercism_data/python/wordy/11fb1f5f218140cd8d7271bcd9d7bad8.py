import re

def calculate(inp):
	inp = re.sub('^What is |\?$', '', inp.strip())
	inp = re.sub(' plus ', '+', inp)
	inp = re.sub(' minus ', '-', inp)
	inp = re.sub(' multiplied by ', '*', inp)
	inp = re.sub(' divided by ', '/', inp)

	# exp implementation
	inp = re.sub('\*\*', '!ERROR!', inp)
	inp = re.sub(' raised to the (.*)(?:st|nd|rd|th) power', '**\\1', inp)

	# For some reason provided unittest would like -3 + 7*-2 to be equal -8
	# rather than -17, which is arguably the correct answer. Oh, well.
	inp = _place_parens(inp)

	try:
		return eval(inp)
	except:
		raise ValueError

def _place_parens(lst):
	""" Places parens in the string so that operations are performed left to right, e.g.:
		1+2*3 => (1+2)*3
	"""
	if not isinstance(lst, list):
		# split string into a list of operands and operators
		return _place_parens(re.split('((?:\+|\-|\*\*?|\/)\-?)', '0+'+lst))
	# recursively join list back into string, adding parens where appropriate
	if len(lst) < 3:
		return "".join(lst)
	return "({}){}{}".format(_place_parens(lst[:-2]), lst[-2], lst[-1])
