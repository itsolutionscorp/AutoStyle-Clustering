#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
	# splits the sentences into words
	words = what.split()
	# strips right blanks
	sentence = what.rstrip()
	# removes punctuation
	noPunct = sentence.translate({ord('.'): None, ord('-'): None, ord('?'): None, ord(','): None})

	# checks if is a question AND if it's all alphanumeric symbols and not all caps
	if sentence.endswith('?') and ([word for word in noPunct if word.isalnum()] and [word for word in words if not word.isupper()]):
		return 'Sure.'
	# checks if all words are uppercase and none are lowercase
	elif [word for word in words if word.isupper()] and not [word for word in words if word.islower()]:
		return 'Whoa, chill out!'
	# checks if all characters are alphanumeric
	elif [word for word in noPunct if word.isalnum()]:
		return 'Whatever.'
	else:
		return 'Fine. Be that way!'
