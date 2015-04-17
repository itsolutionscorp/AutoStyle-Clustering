def slices(digits, window):
    if len(digits)<window: raise ValueError("String length is less than slice")
    if not window: raise ValueError("Invalid slice size")
    if not digits.isdigit(): raise ValueError("String is not digit")
    digit_list = [int(x) for x in list(digits)]
    windows = (len(digits) - window) + 1
    return [digit_list[x:x+window] for x in range(windows)]
