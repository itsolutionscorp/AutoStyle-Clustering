def hamming(dna1, dna2):
	length, diff = find_smallest(dna1, dna2)
	hamming_dist = 0

	for pos in range(0, length):
		if dna1[pos] != dna2[pos]: 
			hamming_dist += 1

	return hamming_dist + diff

# takes two strings
# returns a list of the smaller length, and the difference
# ex. [5, 2]
def find_smallest(str1, str2):
	str1_len = len(str1)
	str2_len = len(str2)

	if str1_len == str2_len: 
		return [str1_len, 0]
	elif str1_len > str2_len:
		return [str2_len, str1_len - str2_len]
	else:
		return [str1_len, str2_len - str1_len]
