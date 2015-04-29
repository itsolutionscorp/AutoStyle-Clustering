
def slices(number_string, length):
    slices_list = []
    if length <= 0 or len(number_string) < length:
        raise ValueError
    else:
        for i in range(0, len(number_string)-length+1):
            max = i+length
            list = [int(c) for c in number_string[i:max]]
            slices_list.append(list)
        return slices_list


