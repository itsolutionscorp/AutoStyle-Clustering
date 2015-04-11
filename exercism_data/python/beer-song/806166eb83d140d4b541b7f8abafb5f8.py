_verse = """{num_bottles_cap} of beer on the wall, {num_bottles} of beer.
{action}, {num_bottles_next} of beer on the wall.
"""

def _bottle_text(n):
	if n == 0:
		return 'no more bottles'
	if n == 1:
		return '1 bottle'
	return '{n} bottles'.format(n=n)

def _action_text(n):
	if n == 0:
		return 'Go to the store and buy some more'
	if n == 1:
		return 'Take it down and pass it around'
	return 'Take one down and pass it around'

class Beer(object):
	def verse(self, num):
		bottle_text = _bottle_text(num)
		bottle_next = _bottle_text(num - 1) if num > 0 else _bottle_text(99)
		return _verse.format(num_bottles_cap=bottle_text.capitalize(),
							 num_bottles=bottle_text,
							 action=_action_text(num),
							 num_bottles_next=bottle_next)

	def sing(self, hi, lo=0):
		song = ''
		for i in range(hi, lo-1, -1):
			song += self.verse(i) + '\n'
		return song
