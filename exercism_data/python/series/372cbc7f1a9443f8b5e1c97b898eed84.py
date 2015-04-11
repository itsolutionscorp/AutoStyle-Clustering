def slices(test, size):
	if size > len(test):
		raise ValueError("Stop being a smart ass. Your string smaller than your size.")
	if size < 1:
		raise ValueError("Stop being a smart ass. You need a positive size.")
	return [[int(j) for j in test[i:i+size]] for i in range(len(test)-size+1)]
