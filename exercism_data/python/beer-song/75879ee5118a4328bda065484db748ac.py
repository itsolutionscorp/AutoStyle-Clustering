def _bottle_text(n):
	count = 'no more' if n == 0 else str(n)
	plurality = '' if n == 1 else 's'
	return '{} bottle{}'.format(count, plurality)

def _action_text(n):
	if n == 0:
		return 'Go to the store and buy some more'
	quantity = 'it' if n == 1 else 'one'
	return 'Take {} down and pass it around'.format(quantity)

def _status_text(n):
	b = _bottle_text(n)
	return "{} of beer on the wall, {} of beer.\n".format(b.capitalize(), b)

def _next_text(n):
	next_quantity = 99 if n == 0 else n - 1
	return "{}, {} of beer on the wall.\n".format(_action_text(n), _bottle_text(next_quantity)) 

class Beer(object):
	def verse(self, n):
		return _status_text(n) + _next_text(n)

	def sing(self, hi, lo=0):
		return '\n'.join(self.verse(i) for i in xrange(hi, lo-1, -1)) + '\n'
