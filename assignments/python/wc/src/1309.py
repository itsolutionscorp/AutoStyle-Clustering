def word_count(text):
	import re
	text = re.sub('\s{2,}'," ", text)
	words = re.split('\s', text)
	words = sorted(words)
	summary = {}
	count = 0
	while words:
		word = words[-1]
		if not word in summary:
			summary[word] = 1
		else:
			key = word
			count = summary[word] + 1
			summary.update({key: count})
			count = 0
		del words[-1]
	return summary
