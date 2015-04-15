def slices(number, length):
    
    return_list = [map(int, list(number[int(i) : int(i) + length])) for i in range(len(number)) if len(number[int(i) : int(i) + length]) == length]

    if length > len(number):
        raise ValueError('There are no results')
    
    return return_list

def largest_product(number, length):
    if slices(number, length):
        return max(reduce(lambda x, y: x*y, i) for i in slices(number, length))
    else:
        return 1
