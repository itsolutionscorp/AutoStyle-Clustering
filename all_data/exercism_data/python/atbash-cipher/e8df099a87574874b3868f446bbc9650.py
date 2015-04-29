import re, string


def decode(s):
	return _clean_and_substitute(s)

def encode(s):
	return _split_into_groups(_clean_and_substitute(s))

translation_table = string.maketrans(string.ascii_lowercase, string.ascii_lowercase[::-1])
def _clean_and_substitute(s):
	""" Remove non-cipherable chars from string and apply cipher substitution.
		Since cipher is symmetric, this is used for both encoding and decoding.
	"""
	return _only_alnum(s).lower().translate(translation_table)

def _split_into_groups(s):
	""" Insert a space character after each 5 characters in a string.
	"""
	return ' '.join([s[i:i+5] for i in range(0, len(s), 5)])

regex_non_alpha_numeric = re.compile('[^a-zA-Z0-9]+')
def _only_alnum(s):
	""" Removes all non-alphanumeric characters from a string.
	"""
	return regex_non_alpha_numeric.sub('', s)
