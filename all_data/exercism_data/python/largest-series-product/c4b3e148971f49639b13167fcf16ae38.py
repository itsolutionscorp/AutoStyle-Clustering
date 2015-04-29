
def slices(number_string, length):
    slices_list = []
    if len(number_string) < length:
        raise ValueError
    else:
        for i in range(0, len(number_string)-length+1):
            max = i+length
            slices_list.append([int(c) for c in number_string[i:max]])
        return slices_list


def largest_product(number_string, length):
    largest = 0
    lists = slices(number_string, length)
    for item in lists:
        product = 1
        for i in item:
            product *= i
        if product > largest:
            largest = product
    return largest
