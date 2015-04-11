#Count words, MapReduce style

def word_count(input):
	dic = Counter()
	for word in input.split():
		dic[word] += 1
	return dic
		
		
class Counter(dict):
	def __missing__(self, key):
		return 0
