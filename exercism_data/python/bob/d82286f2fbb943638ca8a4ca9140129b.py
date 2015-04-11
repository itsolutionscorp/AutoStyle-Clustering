def hey(sentence):
    if sentence.strip() == '':
	return "Fine. Be that way!"
    elif sentence[:-1].isupper():
	return "Whoa, chill out!"
    elif sentence[-1] == '?':
        return "Sure."
    else:
	return "Whatever."
