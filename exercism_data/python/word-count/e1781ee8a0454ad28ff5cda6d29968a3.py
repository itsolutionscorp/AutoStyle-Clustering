def word_count (text):
	words = text.split()
	final = {}
	for word in words:
		if word in final:
			final[word] += 1
		else:
			final[word] = 1


	return final
