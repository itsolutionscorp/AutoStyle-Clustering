import re, string, itertools, random

class Cipher(object):

	@staticmethod
	def generate_key():
		""" Return string of random characters suitable for use as a cipher key.
		"""
		return ''.join(random.SystemRandom().choice(string.ascii_lowercase) for _ in range(123))

	def __init__(self, key=''):
		self.key = _only_letters(key.lower())
		if not self.key:
			self.key = Cipher.generate_key()

	def encode(self, message):
		message = _only_letters(message.lower())
		return self._convert(message, lambda char_i, key_i: char_i + key_i)

	def decode(self, message):
		return self._convert(message, lambda char_i, key_i: char_i - key_i)

	def _convert(self, message, get_leter_index):
		""" Applies a transformation to each character in `message`
		"""
		ord_a = ord('a')
		return "".join(
			_nth_letter(get_leter_index(ord(char) - ord_a, ord(key_char) - ord_a))
				for char, key_char in zip(message, itertools.cycle(self.key))
		)

class Caesar(Cipher):
	def __init__(self):
		Cipher.__init__(self, 'd')


_regex_non_letters = re.compile('[^a-z]+')
def _only_letters(s):
	""" Removes all non-letters characters from a string.
	"""
	return _regex_non_letters.sub('', s)

def _nth_letter(n):
	""" Return `n`-th letter of an endlessly repeated alphabet
	"""
	return string.ascii_lowercase[n % len(string.ascii_lowercase)]
