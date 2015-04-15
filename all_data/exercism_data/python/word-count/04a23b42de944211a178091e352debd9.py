import string
from collections import defaultdict

#I'm aware that 
# for word in phrase.translate(table, string.punctuation).lower().split():
# would work just as well, I'm just being explicit

def word_count(phrase):
	#make translate table, apparently necessary to use .translate
	table = string.maketrans('','')
	#maps every letter the same, with string.punctuation removed
	phrase = phrase.translate(table, string.punctuation)
	#make all lower case since case doesn't matter
	phrase = phrase.lower()
	#split into word list to iterate over
	word_list = phrase.split()
	# set up counts dictionary
	counts = defaultdict(int)
	for word in word_list:
		counts[word] += 1
	return counts
