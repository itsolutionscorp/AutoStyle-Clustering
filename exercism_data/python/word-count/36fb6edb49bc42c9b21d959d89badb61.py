import re
from collections import defaultdict

def word_count(s):
	word_dict = defaultdict(int)

	s = re.sub(r"\W+"," ",s)
	s = s.lower()
	words = s.split()

	for word in words:
		word_dict[word] += 1

	return dict(word_dict)
