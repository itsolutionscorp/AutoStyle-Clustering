#
# Skeleton file for the Python "Bob" exercise.
#
def hey(Sentence):

	if Sentence.strip() == '':
		return "Fine. Be that way!"
	else:
		noSpaces = Sentence.strip()

		if(noSpaces.isupper()):
			return "Whoa, chill out!"

		if(noSpaces.endswith('?')):
			return "Sure."

		return "Whatever."
