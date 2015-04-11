def slices(numbers, length):
	"""Find all possible consecutive number series of length `n` in a string of numbers"""
	numNumbers = len(numbers)

	if length == 0: raise ValueError('length can not be zero.')
	if length > numNumbers: raise ValueError(str(length) + ' is longer than ' + numbers)
	if length == 1: return [[int(number)] for number in numbers]

	numbers = [int(number) for number in numbers]							# Turn 'numbers' into a list of numbers
	sliceList = []

	for step in range(numNumbers - length + 1):
		sliceList.append(numbers[step:step + length])						# Create all the relevant slices

	return sliceList


# Author's Notes on Coding Conventions:

	# I've coded to pep8 conventions with a few exceptions that
	# work nicely with my setup (I'm only coding for myself):
		# ignore = W191,E261,W293,E701
		# max-line-length = 120


# # Series

# Write a program that will take a string of digits and give you all the possible consecutive
# number series of length `n` in that string.

# For example, the string "01234" has the following 3-digit series:

# - 012
# - 123
# - 234

# And the following 4-digit series:

# - 0123
# - 1234

# And if you ask for a 6-digit series from a 5-digit string, you deserve
# whatever you get.


# - - - EARLY CODE - - - #

# I initially misunderstood and read consecutive number as sequntial number.
# This ended up being a more interesting problem to solve, I'm kind of sorry
# that wasn't the problem.


# SECOND TRY - Then I thought the *set* of numbers had to be consecutive. Woops.

# def slices(numbers,length):
# 	"""Find all possible consecutive number series of length `n` in a string of numbers"""
# 	numNumbers = len(numbers)

# 	if length == 0: raise ValueError('length can not be zero.')
# 	if length > numNumbers: raise ValueError(str(length) + ' is longer than ' + numbers)
# 	if length == 1: return [[int(number)] for number in numbers]

# 	numbers = [int(number) for number in numbers]
# 	sliceList = []

# 	for step in range(numNumbers - length + 1):
# 		tempSlice = sorted(numbers[step:step + length])
# 		if (tempSlice[0] + length - 1) == tempSlice[-1]:
# 			sliceList.append(numbers[step:step + length])

# 	return sliceList


# FIRST TRY - I thought each number had to be consecutive with the previous number.

# def slices(numbers,length):
# 	"""Find all possible consecutive number series of length `n` in a string of numbers"""
# 	numNumbers = len(numbers)

# 	if length == 0: raise ValueError('length can not be zero.')
# 	if length > numNumbers: raise ValueError(str(length) + ' is longer than ' + numbers)
# 	if length == 1: return [[int(number)] for number in numbers]

# 	numbers = [int(number) for number in numbers]

# 	sliceList = []
# 	possibleSlices = []

# 	for n in range(numNumbers):
# 		tempSlices = []
# 		for possible in possibleSlices:
# 			print '\t', possible, numbers[n], possible[-1], possible[0]
# 			if possible[-1] + 1 == numbers[n]:
# 				possible.append(numbers[n])
# 				tempSlices.append(possible)
# 			elif possible[0] - 1 == numbers[n]:
# 				possible.insert(1, numbers[n])
# 				tempSlices.append(possible)

# 		possibleSlices = tempSlices[:]
# 		possibleSlices.append([numbers[n]])

# 		print possibleSlices

# 		if len(possibleSlices[0]) == length:
# 			sliceList.append(possibleSlices.pop(0))
# 			print '\t', sliceList
		
# 	return sliceList


# Testing Code

# if __name__ == '__main__':
# 	print slices("97867564", 3)
# 	print [[9, 7, 8], [7, 8, 6], [8, 6, 7],[6, 7, 5], [7, 5, 6], [5, 6, 4]]

# 	print slices("97867564", 2)
# 	print [[9, 7], [7, 8], [8, 6], [6, 7], [7, 5], [5, 6], [6, 4]]
