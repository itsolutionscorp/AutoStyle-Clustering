from functools import reduce
import operator

def slices(stringNumbers, groups):
	if groups > len(stringNumbers):
		raise ValueError
	listNumbers = [int(numb) for numb in stringNumbers]
	newList = []
	for i in range(0, len(listNumbers) - groups + 1):
		 newList.append(listNumbers[i:(i+groups)])
	return newList

def largest_product(stringNumbers, groups):
	if groups == 0:
		return 1
	elif groups == len(stringNumbers):
		listNumbers = [int(numb) for numb in stringNumbers]
		return reduce(operator.mul, listNumbers)
	else:
		listedGroups = slices(stringNumbers, groups)
		prodList = []
		for i in listedGroups:
			prodList.append(reduce(operator.mul, i))
		return max(prodList)
