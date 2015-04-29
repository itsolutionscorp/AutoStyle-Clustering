def word_count (text):
	words = text.split()
	final = {}
	final = {x: words.count(x) for x in words}
	return final
