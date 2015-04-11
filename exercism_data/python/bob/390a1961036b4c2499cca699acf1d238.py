import collections

RESPONSES = collections.OrderedDict([
	(lambda what: what.isupper(), 'Whoa, chill out!'),
	(lambda what: what.endswith('?'), 'Sure.'),
	(lambda what: not what or what.isspace(), 'Fine. Be that way!'),
	(lambda what: True, 'Whatever.'),
])

def hey(what):
	for pat, resp in RESPONSES.items():
		if pat(what):
			return resp
	else:
		raise ValueError("Unknown input: {!r}".format(what))
