#
# Skeleton file for the Python "Bob" exercise.
#

import re
import unicodedata

def hey(what):

	what = what.strip()
	what = unicodedata.normalize('NFKD', what).encode('ASCII', 'ignore')

	if len(what) == 0: return 'Fine. Be that way!'

	if re.search("[A-Z]", what) and not re.search("[a-z]", what): return 'Whoa, chill out!'

	if what.endswith('?'): return 'Sure.'

	return 'Whatever.'
