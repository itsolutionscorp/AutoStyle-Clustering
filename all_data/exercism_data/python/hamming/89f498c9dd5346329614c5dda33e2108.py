def hamming(str1, str2):
	return sum(a != b for a, b in map(None, str1, str2))

if __name__ == '__main__':
	print hamming('AAGCTAC', 'ACGTT')

