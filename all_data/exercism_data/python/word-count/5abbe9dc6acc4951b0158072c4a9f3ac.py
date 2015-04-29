def word_count(statement):
	mystring = "".join([c for c in statement if c.isalnum() or c.isspace()])
	words = mystring.lower().split();
	out={};
	while(len(words)):
		word = words[0]
		counter = 1
		words.remove(word)
		while(word in words):
			counter+=1
			words.remove(word)
		out.update({word : counter})
	return out;
