from collections import defaultdict


import re 
def word_count(sentence):
	sentence=re.sub(r'[^a-zA-z0-9!&@$%?:^]+',',',sentence)
	a=sentence.split(',')
	words = defaultdict(int)
	for word in a:
		words[word]+=1
	return words

print word_count("car : carpet as java : javascript!!&@$%^&")
