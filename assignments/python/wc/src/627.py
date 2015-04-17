import re
def word_count(string):
	phrase = {}
	string = re.sub("[^\w]", " ", string).lower().split()
	while string:
		x = string.pop(0)
		if x in phrase:
			phrase[x] += 1
		else:
			phrase[x] = 1
	return phrase
