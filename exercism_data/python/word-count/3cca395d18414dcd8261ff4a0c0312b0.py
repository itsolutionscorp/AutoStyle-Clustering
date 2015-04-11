phrase = raw_input("Please enter a phrase: ")

def count_words(phrase):
	
	word_list = phrase.split(" ")

	for w in set(word_list):
		print "%s: %s" %(w, word_list.count(w))

count_words( phrase );
