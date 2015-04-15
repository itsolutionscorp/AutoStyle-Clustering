# sieve.py

def _remove_from_list(list, n):
	retList = []
	for i in list:
		if i % n != 0 or i == n:
			retList.append(i)
	return retList

def sieve(num):
	primeList = range(2,num+1)
	n = 0
	while True:
		primeList=_remove_from_list(primeList, primeList[n])
		n = n + 1
		if n >= len(primeList):
			return primeList
		
