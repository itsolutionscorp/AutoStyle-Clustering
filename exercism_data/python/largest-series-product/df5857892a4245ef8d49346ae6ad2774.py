def prod(numlist):
	out = 1
	for i in numlist:
		out*=i
	return out

def slices(test, size):
	if size > len(test):
		raise ValueError("Stop being a smart ass. Your string smaller than your size.")
	if size < 0:
		raise ValueError("Stop being a smart ass. You need a positive size.")
	return [[int(j) for j in test[i:i+size]] for i in range(len(test)-size+1)]

def largest_product(test, size):
	return max([prod(i) for i in slices(test,size)])
