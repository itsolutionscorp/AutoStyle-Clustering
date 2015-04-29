# thank you mherk ... version 2 directly forms dict

def word_count (text):
	words = text.split()
	final = {}

	final = {x: words.count(x) for x in words}

	return final
