def hey(word):
	chars = set('abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ')
	if word.isspace() == True or word == "":
		return "Fine. Be that way!"
	elif word.upper() == word and any((c in chars) for c in word):
		return "Whoa, chill out!"
	elif "!" not in word and "?" not in word:
		return "Whatever."
	elif word[len(word)-1] == '?':
		return "Sure."
	else:
		return "Whatever."
