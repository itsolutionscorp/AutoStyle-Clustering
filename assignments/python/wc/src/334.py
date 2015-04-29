def word_count(seq):
	res = dict()
	try:
		words = seq.split()
		for w in words:
			if w in res:
				res[w] += 1
			else:
				res[w] = 1
	except AttributeError:
		pass
	finally:
		return res
