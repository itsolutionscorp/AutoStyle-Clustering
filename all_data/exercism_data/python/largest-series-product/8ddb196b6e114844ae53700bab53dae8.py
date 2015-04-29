def slices(string, of_length):
    if of_length > len(string) or of_length == 0:
        raise ValueError("Invalid length for slice. Must be greater than 0 and less than string length. String = " +
                           string + " length = " + str(of_length))
    result = []
    for i in range(0,len(string)):
        current_slice = map(int,list(string[i:i+of_length]))
        if len(current_slice) == of_length:
            result.append(current_slice)
    return result

def largest_product(string, of_length):
    if of_length == 0:
        return 1
    return max(reduce(lambda x, y: x*y, s) for s in slices(string, of_length))
