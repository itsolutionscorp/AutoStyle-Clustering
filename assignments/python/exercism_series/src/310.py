def slices(digits_str, length):
    """
    Takes a string of digits and gives all the possible consecutive number
    series of length `length` in that string.
    """
    if length not in range(1, len(digits_str) + 1):
        raise ValueError('Given length not between [1, length of series]')
    range_ = range(0, len(digits_str) - length + 1)
    str_slices = [digits_str[idx:idx+length] for idx in range_]
    return [[int(char) for char in slice_] for slice_ in str_slices]
if __name__ == "__main__":
    print(slices("01234", 4))
