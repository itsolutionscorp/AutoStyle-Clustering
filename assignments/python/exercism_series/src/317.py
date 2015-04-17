def slices(string, substring_length):
    if substring_length == 0 or substring_length > len(string):
        raise ValueError
    digit_list = list(string)
    digit_list = [int(digit) for digit in digit_list]
    substring_list = []
    for i in range(len(digit_list) - substring_length + 1):
        substring_list.append(digit_list[i:i + substring_length])
    return substring_list
