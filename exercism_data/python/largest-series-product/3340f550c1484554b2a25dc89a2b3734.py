def slices(digits, length):
    if not digits:
        return []
    if length > len(digits) or length < 1:
        raise ValueError
    number_of_series = len(digits) - length + 1
    return [map(int,list(digits[i:i+length])) for i in range(number_of_series)]

def largest_product(sequence, length):
    if not sequence: return 1
    sequences = slices(sequence,length)
    products = [reduce(lambda x, y: x * y, sequence) for sequence in sequences]
    return max(products)
