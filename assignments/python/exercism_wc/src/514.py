def word_count(string):
	phrase = {}
	string = string.split()
	while string:
		x = string.pop()
		if x in phrase:
			phrase[x] += 1
		else:
			phrase.update({x: 1})
