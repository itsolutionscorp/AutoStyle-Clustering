def word_count(msg):
	words = msg.split()
	return {k: words.count(k) for k in words}
