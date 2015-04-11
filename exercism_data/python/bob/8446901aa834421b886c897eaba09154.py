#
# "Bob"
#  
# Basically, I run through the characters in the string until 
# I have ruled out the string being all numbers/punctuation or all non-lowercase. 
# Then I check if it's a question, yelling or something else. Since words with all 
# numbers/punctuation belong to a subset of words with all non-lowercase characters, 
# I have to check both for yelling and questioning input.

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
