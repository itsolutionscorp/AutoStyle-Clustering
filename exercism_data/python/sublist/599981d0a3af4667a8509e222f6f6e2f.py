from collections import Counter
SUBLIST = "SUBLIST"
SUPERLIST = "SUPERLIST"
EQUAL = "EQUAL"
UNEQUAL = "UNEQUAL"

def check_lists(left, right):

	len_left = len(left)
	len_right = len(right)

	if len_left == len_right:
		if is_sublist(left, right) != -1:
			return EQUAL
	elif len_left < len_right:
		if is_sublist(left, right) != -1:
			return SUBLIST
	else:
		if is_sublist(right, left) != -1:
			return SUPERLIST

	return UNEQUAL

def is_sublist(needle, hay):
	h = len(hay)
	n = len(needle)
	skip = {needle[i]: n - i - 1 for i in range(n)}
	i = n - 1
	while i < h:
		for j in range(n):
			if hay[i - j] != needle[-j - 1]:
				i += skip.get(hay[i], n)
				break
		else:
			return i - n + 1
	return -1
