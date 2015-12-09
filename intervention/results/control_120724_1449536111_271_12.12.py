def num_common_letters(first, second):
	helper_lst1, helper_lst2 = [], []
	filtered_first = [helper_lst1.append(letter) for letter in first if letter not in helper_lst1]
	filtered_second = [helper_lst2.append(letter) for letter in second if letter not in helper_lst2]
	counter = 0
	for letter in filtered_second:
		if letter in filtered_first:
			counter += 1
	return counter