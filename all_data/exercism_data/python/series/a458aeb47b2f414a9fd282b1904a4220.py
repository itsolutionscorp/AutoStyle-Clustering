def slices(string_of_digits,consecutive_length):
    if consecutive_length > len(string_of_digits) or consecutive_length ==0:
        raise ValueError()
    return_list = []
    x=0
    while x + consecutive_length <= len(string_of_digits):
        return_list.append([int(y) for y in string_of_digits[x : x + consecutive_length]])
        x+=1
    return return_list
