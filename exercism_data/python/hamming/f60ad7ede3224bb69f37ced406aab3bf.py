import itertools
def distance(firstDna, secondDna):
	# count = 0
	# if len(firstDna) == len(secondDna):
	# 	for i,v in enumerate(firstDna):
	# 		if firstDna[i] != secondDna[i]:
	# 			count += 1
	# return count
	count = 0
	for x,y in (itertools.izip_longest(firstDna, secondDna)):
		if x != y:
			count += 1
	return count
