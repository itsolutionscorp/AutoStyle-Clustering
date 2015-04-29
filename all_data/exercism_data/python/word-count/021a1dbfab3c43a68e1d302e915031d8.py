def get_next_word(text, pos):
	while( text[pos] == ' ' or text[pos] == '\t' or text[pos] == '\r' or text[pos] == '\n' ):
		pos = pos + 1
	ref = pos
	word = ''
	while( text[pos] != '' or text[pos] != '\t' or text[pos] != '\r' or text[pos] != '\n' ):
		word[pos-ref] = text[pos]
	return word	

def word_count(text):
	wordArray = dict()
	for word in text.split():
		if word not in wordArray:
			wordArray.update({word:1})
		else:
			wordArray[word] = wordArray[word] + 1
	return wordArray
