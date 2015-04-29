def transform(old):
	# for loops can also do dict comprehension
	return {w.lower():k for k,v in old.iteritems() for w in v}
