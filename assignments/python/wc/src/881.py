import itertools, re
def word_count(text):
	text = re.sub('[,.\n\r\t ]+', ' ', text).strip()
	return {k: len(list(g)) for k,g in itertools.groupby(sorted(text.split(' ' )))}
