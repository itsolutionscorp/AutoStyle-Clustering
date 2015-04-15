# wordcount

def word_count(string):

	countDict = {}

	x = [d for d in string if not d.isalnum()]

	delim =  ''.join(x)

	words = [c.strip(delim).lower() for c in string.split() if len(c.strip(delim)) > 0]

	for word in words:

		if not word in countDict:
			countDict[word] = 1

		else:
			countDict[word] += 1

	return countDict
