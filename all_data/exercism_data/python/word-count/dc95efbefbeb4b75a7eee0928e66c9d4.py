import string

def stripPunc(x):  # From S.Lott's solution
    for i in string.punctuation:
        x = x.replace(i,"")
    while "  " in x:
    	x = x[0:x.find("  ")] + x[(x.find("  ") + 1):]
    return x

def word_count(x):
	words = {}

	x = stripPunc(x)

	x = x.lower()
	splitX = x.split(" ")

	for i in stripPunc(x).lower().split(" "):
		if i in words:
			words[i] += 1
		else:
			words[i] = 1

	return words
