#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
	# Remove whitespace surrounding the text.
	sentence = what.strip();
	# If there's nothing left, then they didn't say anything.
	if len(sentence) == 0:
		return "Fine. Be that way!"
	# We have to count the total number of letters in the sentence
	letterCount = sum(1 for c in sentence if c.isalpha())
	# Then we count the number of capital letters, to determine if they're yelling
	capitalLetters = sum(1 for c in sentence if c.isupper())
	# If we have a non-zero number of letters, and they're all capitals: they're yelling at me.
	if letterCount > 0 and capitalLetters == letterCount:
		return "Whoa, chill out!"
	# Otherwise, check to see if this was a question
	if sentence.find("?") == len(sentence)-1:
		return "Sure."
	# Base case. Whatever.
	return "Whatever."
