def hamming(strand1, strand2):
	strandList = []
	hammingnum = 0
	#Determine which strand is longer
	if len(strand1) >= len(strand2):
		l = strand1
		s = strand2
	else:
		l = strand2
		s = strand1
	#Add random integer (in this case 0) to equalize lengths
	for i in range(len(l)-len(s)):
		s += '0'
	for item in range(len(list(l))):
		#Append tuple containing the letter of each strand at given index
		strandList.append((list(l)[item], list(s)[item]))
	for item in range(len(strandList)):
		#If the two items in each tuple don't match, add 1 to counter
		if strandList[item][0] != strandList[item][1]:
			hammingnum += 1
	return hammingnum
