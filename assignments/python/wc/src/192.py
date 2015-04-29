import re;
def word_count(text):
	words = re.split('[\\n\\t\\s]+', text);
	counter = {};
	for w in words:
		counter[w] = counter[w] + 1 if w in counter else 1;
	return counter;
