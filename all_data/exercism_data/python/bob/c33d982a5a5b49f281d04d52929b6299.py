import re
p = re.compile('[A-Z]')

class Bob(object):
	def hey(self, question):
		if question.strip() == '':
			return 'Fine. Be that way!'
		has_caps = p.search(question) is not None
		has_lower = len([c for c in question if c != c.upper()]) > 0
		if has_caps and not has_lower:
			return 'Woah, chill out!'
		if question.endswith('?'):
			return 'Sure.'
		return 'Whatever.'
