def num_common_letters(first, second):
	def unique_list(string):
		helper_lst = []
		filtered_lst = [helper_lst.append(item) for item in string if item not in helper_lst]
		return filtered_lst
	unique_first, unique_second, counter = unique_list(first), unique_list(second), 0
	for letter in unique_second:
		if letter in unique_first:
			counter += 1
	return counter