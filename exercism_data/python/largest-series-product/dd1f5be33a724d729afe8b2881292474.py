__author__ = 'Hinek'

def slices(string, length):
    if length > len(string):
        raise ValueError
    result = []
    for i in range(len(string) - length + 1):
        result.append([int(i) for i in list(string[i:i+length])])
    return result

def largest_product(string, length):
    parts = slices(string, length)
    greatest = get_list_product(parts[0])
    for part in parts:
        curr = get_list_product(part)
        if curr > greatest:
            greatest = curr
    return greatest

def get_list_product(list):
    if not list:
        return 1
    return reduce(lambda x, y: x*y, list)
