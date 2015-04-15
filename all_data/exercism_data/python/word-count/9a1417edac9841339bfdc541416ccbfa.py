# wordcount
from collections import defaultdict

def word_count(string):
	
	x = [d for d in string if not d.isalnum()]
	delim =  ''.join(x)
	words = [c.strip(delim).lower() for c in string.split() if len(c.strip(delim)) > 0]
	d = defaultdict(int)

	for word in words:
		d[word] += 1

	return dict(d)	
