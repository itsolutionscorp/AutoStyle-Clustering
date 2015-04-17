def word_count(sentence):
	sent = sentence.replace('\n', ' ').split(' ')
	cnt = {}
	for w in sent:
		if w <> '':
			if w in cnt:
				cnt[w] += 1
			else: 
				cnt[w] = 1
	return cnt
