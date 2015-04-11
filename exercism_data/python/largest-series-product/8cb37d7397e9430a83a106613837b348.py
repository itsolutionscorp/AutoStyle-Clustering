def slices(numbers, slice_size):

    if slice_size > len(numbers): raise ValueError('Slice bigger than numbers')

    all_slices = []
    numbers = [int(n) for n in numbers]

    while len(numbers) >= slice_size:
        all_slices.append(numbers[:slice_size])
        numbers.pop(0)

    return all_slices

def product(numbers):
    n = 1

    for number in numbers:
        n *= number

    return n

def largest_product(numbers, slice_size):

    if numbers == '' and slice_size == 0: return 1

    s = slices(numbers, slice_size)

    largest_product_result = 1

    for slice in s:
        if product(slice) > largest_product_result:
            largest_product_result = product(slice)

    return largest_product_result
