from operator import mul

def largest_product(full_set, lengths):
    if not len(full_set) and not lengths:
        return 1
    return max(reduce(mul, items) for items in slices(full_set, lengths))

def slices(full_set, lengths):
    set_len = len(full_set)
    if lengths > set_len or lengths <= 0:
        raise ValueError("The desired slice length is invalid")

    return [map(int, list(full_set[i:i + lengths])) for i in range(set_len - lengths + 1)]
