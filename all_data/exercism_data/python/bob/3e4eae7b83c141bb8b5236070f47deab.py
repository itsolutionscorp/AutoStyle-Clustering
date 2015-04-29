def hey(sentence):
    if sentence.isspace() or sentence == '':
	return "Fine. Be that way!"
    elif sentence[:-1].isupper():
	return "Whoa, chill out!"
    elif sentence[-1] == '?':
        return "Sure."
    else:
	return "Whatever."
