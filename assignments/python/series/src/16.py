def slices(string_of_digits, n):
    if n > len(string_of_digits) or n <= 0:
        raise ValueError('no series of that length for sequence provided')
    str_series = [list(string_of_digits[i:i+n])
                  for i, _ in enumerate(string_of_digits)
                  if len(string_of_digits[i:i+n]) == n]
    return [[int(val) for val in sub_list] for sub_list in str_series]
