def distance(base_str, compare_str):
	if base_str == compare_str:
		return 0
	else:
		base_str_list = list(base_str)
		compare_str_list = list(compare_str)
		hamming_counter = 0

		for index in range(len(base_str_list)):
			if (base_str_list[index] != None) & (compare_str_list[index] != None):
				if base_str_list[index] != compare_str_list[index]:
					hamming_counter += 1

		return hamming_counter
