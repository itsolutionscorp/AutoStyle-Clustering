def word_count(sentence):
	words = sentence.split()
	final = {}
	for i in range(len(words)):
		count = 0
		for n in words:
			if n == words[i]:
				count = count + 1
		final.update({words[i] : count})
	return final		
		 
		
		
		
