

def hamming_old(str1, str2):
	distance = -1
	if str1 == str2:
		return 0
	if len(str1) != len(str2):
		short_string = min([str1, str2], key=len)
		long_string = max([str1, str2], key=len)
		short_string = short_string + '0'*(len(long_string) - len(short_string))
		#print zip(short_string, long_string)
		return [x == y for (x, y) in zip(short_string, long_string)].count(False)
	return [x == y for (x, y) in zip(str1, str2)].count(False)

def hamming(str1, str2):
	#print [a==b for a, b in map(None, str1, str2)]
	return sum(a != b for a, b in map(None, str1, str2))


if __name__ == '__main__':
	print hamming('AAGCTAC', 'ACGTT')

