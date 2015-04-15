def hamming(string1, string2):

	ham_count = 0

	if len(string1) > len(string2):
		longest_string, shortest_string = string1, string2
	else:
		longest_string, shortest_string = string2, string1

	for i in range(len(longest_string)):
		if i > len(shortest_string) - 1 or longest_string[i] != shortest_string[i]:
				ham_count = ham_count + 1

	return ham_count			
