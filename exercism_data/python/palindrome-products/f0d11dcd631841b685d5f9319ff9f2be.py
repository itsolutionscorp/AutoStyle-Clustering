def smallest_palindrome(max_factor ,min_factor=0):
	return _findPalindrome(max_factor,min_factor,min)


def largest_palindrome(max_factor,min_factor=0):
	return _findPalindrome(max_factor,min_factor,max)


def _findPalindrome(max_factor,min_factor,func):
	factorsList,numsList = _palindrome(range(min_factor,max_factor+1))
	funcNums = func(numsList)
	inx = [i for i in range(len(numsList)) if numsList[i]==funcNums]
	retValue = numsList[inx[0]]
	factors = set(factorsList[inx[0]])
	# Unit tests require only one factor listing returned
	# Despite the factorization of the maximum number
	# may not necessarily be complete. The code below produces
	# the correct output.
	# factors = []
	# for i in inx:
	# 	if factorsList[i] not in factors:
	# 		factors.append[j]
	# factors = [set(i) for i in factors]
	return tuple([retValue,factors])

def _palindrome(numList):
	factors = []
	nums = []
	for i in numList:
		for j in numList:
			if str(i*j) == str(i*j)[::-1]:
				factors.append(set([i,j]))
				nums.append(i*j)
	return [factors,nums]
