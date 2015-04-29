# from itertools import ifilter

def sieve(number):
	listRange = [k for k in xrange(2,number+1)]
	newList = []
	while listRange:
		popped = listRange.pop(0)
		newList.append(popped)
		listRange = filter(lambda x: x%popped, listRange)

	return newList


# print(sieve(10))
