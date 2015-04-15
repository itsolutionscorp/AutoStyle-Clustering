def hexa(n):
	hexDict= {
		'0': 0,
		'1': 1,
		'2': 2,
		'3': 3,
		'4': 4,
		'5': 5,
		'6': 6,
		'7': 7,
		'8': 8,
		'9': 9,
		# Support for multiple cases.
		'a': 10,
		'b': 11,
		'c': 12,
		'd': 13,
		'e': 14,
		'f': 15,
		'A': 10,
		'B': 11,
		'C': 12,
		'D': 13,
		'E': 14,
		'F': 15,
	}
	num = 0
	n = n[::-1]
	for i in range(len(n)):
		if n[i] not in hexDict:
			raise ValueError("Must be hexadecimal input")
		num+= hexDict[n[i]]*(16**i)
	return num
