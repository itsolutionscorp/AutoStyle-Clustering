import re
def word_count(string):
	count={}
	string = re.sub(r'[^a-z0-9\s]','',string.lower())
	split = string.split()
	for char in split:
		if char in count.keys():
			count[char] = count[char]+1
		else:
			count[char] = 1
	return count
