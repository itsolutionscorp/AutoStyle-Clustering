import re
def word_count(inp):
	inp = re.sub(r'[^a-zA-Z0-9\s]','', inp).split(" ")
	outp = {}
	for word in inp:
		if not word:
			continue
		word = word.lower()
		try:
			outp[word] += 1
		except KeyError:
			outp[word] = 1
	return outp
