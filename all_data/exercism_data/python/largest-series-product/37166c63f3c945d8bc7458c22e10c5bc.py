__author__ = 'emiller42'


def slices(sequence, length):

    # handle bad input

    if length == 0:
        raise ValueError("Length argument must be > 0")

    if len(sequence) < length:
        raise ValueError("Length argument cannot be larger than the length of the sequence")

    # Turn the sequence into a list
    list_sequence = list(sequence)

    # initialize output list
    return_list = []

    for i in range(0, len(sequence) - length + 1):
        return_item = []
        for j in range(i, i + length):
            return_item.append(int(list_sequence[j]))
        return_list.append(return_item)

    return return_list


def largest_product(sequence, length):

    if length == 0:
        return 1

    sequences = slices(sequence, length)

    largest = 0

    for item in sequences:
        product = 1
        for num in item:
            product *= num

        if product > largest:
            largest = product

    return largest
