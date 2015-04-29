def hey(sentence):
    sentence = sentence.strip()
    if sentence == "":
    	return "Fine. Be that way!"
    if sentence.isupper():
    	return "Woah, chill out!"
    if sentence[-1] == '?':
    	return "Sure."
    return "Whatever."
