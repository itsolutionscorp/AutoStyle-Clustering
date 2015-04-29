def word_count(message):
	message = (' '.join(message.split())).split(' ')
	final_count = {}
	for i in message:
		final_count[i] = message.count(i)
	return final_count
