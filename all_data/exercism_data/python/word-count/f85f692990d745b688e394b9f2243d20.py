def word_count(string):
	string = string.lower()
	#stringSplit = re.split('[a-zA-Z_]', string)
	stringSplit = string.split(" ")
	countList = []
	stringList = []
	stringDupli = []
	stringDict = {}
	for word in stringSplit:
		if word not in stringDupli:
			i = stringSplit.count(word)
			countList.append(i)
			stringDupli.append(word)
	for word in stringDupli:
		k = word
		i = stringDupli.index(word)
		v = countList[i]
		stringDict[k] = v
	return stringDict
