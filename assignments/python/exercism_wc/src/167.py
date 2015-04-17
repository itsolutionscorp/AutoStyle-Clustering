from collections import Counter
def word_count(str):
	words = str.split()
	counts = Counter(words)
	return counts
if __name__ == '__main__':
	word_count('Lorem Ipsum dolor sit amet.')
