def word_count(prompt):
	words = prompt.split()
	res = {x: words.count(x) for x in words}
	return res

# Thanks to OxfordMBK for the hint!
