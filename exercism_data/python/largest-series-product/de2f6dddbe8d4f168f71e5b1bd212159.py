def slices(num_string, chunk_size):
    if chunk_size > len(num_string):
        raise ValueError
    slices = []
    for i in range(0, len(num_string) - chunk_size + 1):
        slices.append([int(i) for i in num_string[i:i+chunk_size]])
    return slices


def largest_product(num_string, chunk_size):
    if chunk_size > len(num_string):
        raise ValueError
    my_slices = slices(num_string, chunk_size)
    largest = 0
    for nums in my_slices:
        current = 1
        for i in nums:
            current *= i
        if current > largest:
            largest = current
    return largest
