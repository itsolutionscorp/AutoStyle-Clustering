def word_count(text):
	if not isinstance(text, "".__class__):
		raise ValueError("text must be a string")
	ocurrences = {}
	for word in text.split(): 
		ocurrences[word] = ocurrences.get(word,0) + 1
	return ocurrences
