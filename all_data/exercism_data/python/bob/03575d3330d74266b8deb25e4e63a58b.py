#
# "Bob"
#

from string import whitespace
def hey(what):

	# removes whitespace
	what = ''.join(what.split())

	# ... says nothing
	if what == '':
		return 'Fine. Be that way!'

	# test variables
	caps = True
	nums_punct = True

	for char in what:
		if nums_punct or caps:
			# unicode
			uni = ord(char)
			# not num or punctuation
			if not (uni >= 33 and uni <= 64):
				nums_punct = False
			# lowercase (not uppercase)
			if ((uni >= 97 and uni <= 122) or (uni >= 224 and uni <= 255)):
				caps = False
		else:
			break

	# question
	if what[-1:] == '?':
		if caps and not nums_punct:
			return 'Whoa, chill out!'
		else:
			return 'Sure.'

	# yelling
	if caps and not nums_punct:
		return 'Whoa, chill out!'

	# other
	return 'Whatever.'
