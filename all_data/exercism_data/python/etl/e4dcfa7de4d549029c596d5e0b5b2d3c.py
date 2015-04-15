def transform(old):
	return {i.lower(): x for x in old for i in old[x]}
	# Don't you just love python? ;)
