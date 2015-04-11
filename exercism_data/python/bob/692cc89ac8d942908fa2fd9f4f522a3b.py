#
# Skeleton file for the Python "Bob" exercise.
#

import re

def hey(what):

	# Strip string to avoid spaces and tabs in empty reply
	def _strip(s):
		return re.sub(r'[\s_]+','',s)
	def is_empty(s):
		# check if string is empty
		return len(_strip(s))==0
	def is_question(s):
		# check if string ends with a question mark
		return _strip(s)[-1] == '?'
	def all_capital(s):
		#strip non-word characters
		non_word=re.sub(r'[^a-zA-Z_]+','',_strip(s))
		# check if a-zA-z chars are uppercase
		return non_word.isupper();
	if is_empty(what):
		print 'Fine. Be that way!'
		return 'Fine. Be that way!'
	if all_capital(what):
		print 'Whoa, chill out!'
		return 'Whoa, chill out!'

	if is_question(what):
		print "Sure."
		return 'Sure.'
	print "Whatever."
	return "Whatever."

	
hey("")
