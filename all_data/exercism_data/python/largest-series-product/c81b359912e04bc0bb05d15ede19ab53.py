def largest_product(number, slice_length):
    return max(generate_products(number, slice_length))

def generate_products(number, slice_length):
    for slice in slices(number, slice_length):
        yield reduce(lambda x, y: int(x) * int(y), slice, 1)

def slices(number, slice_length):

    if (slice_length > len(number)):
        raise ValueError("Parameter 'slice_length' exceeds the number's length")

    digits = [ int(n) for n in number ] 
    max_slice_offset = len(digits) - slice_length

    return [
        digits[offset:slice_length + offset]
        for offset in range(0, max_slice_offset + 1)
    ]
