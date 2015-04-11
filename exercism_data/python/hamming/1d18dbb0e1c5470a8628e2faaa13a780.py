def distance(section_1, section_2):
	hamming_total = 0
	for index in range(len(section_1)):
		if section_1[index] != section_2[index]:
			hamming_total += 1
	return hamming_total
