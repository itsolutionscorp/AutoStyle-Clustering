def largest_product(series, product):
    return max(reduce(lambda x, y: x * y, digits, 1) for digits in slices(series, product))

def slices(series, product):
    if len(series) < product:
        raise ValueError("Series is too short to build slices")

    result = [map(int, series[x:x+product]) for x in range(len(series) - product + 1)]
    return map(list, result)
