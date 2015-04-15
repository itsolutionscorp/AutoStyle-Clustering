def sum_of_multiples(max,listOfMultibutes=[3,5]):
	sumOfNumbers = 0
	for x in xrange(max):
		for number in listOfMultibutes:
			try:
				if (x%number == 0):
					sumOfNumbers += x
					break
			except:
				pass
	return sumOfNumbers
