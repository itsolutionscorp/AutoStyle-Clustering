def word_count(seq):
	res = dict()

	# We make sure we are dealing with a sequence for good measure
	try:
		words = seq.split()
		for w in words:
			if w in res:
				res[w] += 1
			else:
				res[w] = 1
	except AttributeError:
		# It's not a sequence, we will let finally return and empty dictionary
		pass
	finally:
		return res
