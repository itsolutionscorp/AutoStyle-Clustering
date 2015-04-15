def word_count(sentence):

	word_list = {}

	for word in sentence.split():
		word_list[word] = count(word, sentence)

	return word_list


def count(word, sentence):

	cnt = 0

	for w in sentence.split():
		if word == w:
			cnt += 1
	return cnt
