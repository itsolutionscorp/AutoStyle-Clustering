def slices(str, slice_len):
	if (slice_len <= 0): raise ValueError("Can not have a slice of length < 1")
	str_len = len(str)
	if (slice_len > str_len): raise ValueError("Can not have slices longer that the source string")
	digits  = list(map(int, str))
	return [digits[x : x + slice_len] for x in range(str_len - slice_len + 1)]
