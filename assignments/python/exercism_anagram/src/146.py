import string
def detect_anagrams(word, word_dict):
	
	#Build list with letter counts
	letter_count = [0]*26
	word = word.lower()
	for i in range(len(word)):
		letter_count[ord(word[i]) - ord('a')] += 1
	anagrams = []
	for words in word_dict:
		#Ignore case and be sure that length of words are the same.
		if words.lower() != word and len(words) == len(word):
			#Make copy of list so that we don't modify original
			temp = list(letter_count)
			index = 0
			
			while index < len(words):
				letter = ord(words[index].lower()) - ord('a')
				index += 1
				if temp[letter] == 0:
					break
				else:
					temp[letter] -= 1	
			
			if sum(temp) == 0:
				anagrams.append(words)
	return anagrams
print detect_anagrams('Orchestra',
                            'cashregister Carthorse radishes'.split())
