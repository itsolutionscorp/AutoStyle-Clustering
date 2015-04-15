import collections
import re
RESPONSES = collections.OrderedDict([
	(lambda what: what.isupper(), 'Whoa, chill out!'),
	(re.compile(r'^.*\?$'), 'Sure.'),
	(re.compile(r'^[\s]*$'), 'Fine. Be that way!'),
	(re.compile(r'.*'), 'Whatever.'),
])

def hey(what):
	for pat, resp in RESPONSES.items():
		if isinstance(pat, str): # Simple string
			if pat == what:
				return resp
		elif callable(pat):
			if pat(what):
				return resp
		elif hasattr(pat, 'search'): # Regular expression
			if pat.search(what):
				return resp
		else:
			raise RuntimeError("Unknown pattern: {!r}".format(pat))
	else:
		raise ValueError("Unknown input: {!r}".format(what))
