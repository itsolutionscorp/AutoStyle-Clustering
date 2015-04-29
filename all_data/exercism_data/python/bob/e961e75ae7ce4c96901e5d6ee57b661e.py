#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
	# Get rid of all trailing whitespace
	strippedText = what.strip()
	# Response to yell (all uppercase):
	if strippedText.lower() != strippedText.upper() and strippedText.upper() == strippedText:
		return 'Whoa, chill out!'
	# Response to saying nothing (empty string):
	if len(strippedText) == 0:
		return 'Fine. Be that way!'
	# Response to a question (ends with '?'):
	if strippedText[-1] == '?':
		return 'Sure.'
	# Response to anything else
	return "Whatever."
