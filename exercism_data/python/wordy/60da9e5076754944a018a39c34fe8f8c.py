import operator
import re

NOP = lambda x, y: x

op_code = {
	' raised to the ' : lambda x, y: x**y,
	'st power' : NOP,
	'nd power' : NOP,
	'rd power' : NOP,
	'th power' : NOP,
	' plus ' : operator.add,
	' minus ' : operator.sub,
	' multiplied by ' : operator.mul,
	' divided by ' : operator.div,
	'What is ' : lambda x, y: y,
	'?' : NOP
}

splitter = "(" + "|".join(["{0}".format(re.escape(memn)) for memn in op_code]) + ")"

def tokenize(text):
	for part in re.split(splitter, text):
		if part:
			yield part

def parse(tokens):
	for token in tokens:
		if token in op_code:
			yield ('OP', op_code[token])
		elif all(char in "-0123456789" for char in token):
			yield ('INT', int(token))
		else:
			raise ValueError("invalid token: ", token)

def look_ahead(tokens):
	last, curr = None, None
	for token in tokens:
		last, curr = curr, token
		if last is not None:
			yield last, curr

def calculate(text):
	if not text.startswith("What is "):
		raise ValueError("Start with 'What is '")

	acc = None
	for curr, look in look_ahead(parse(tokenize(text))):
		if curr[0] == 'OP' and look[0] == 'INT':
			acc = curr[1](acc, look[1])

	return acc
