def slices(series, number):
    if number < 1 or number > len(series):
        raise ValueError

    seriesintlist = [int(s) for s in series]
    output = []
    for i in range(len(series) - number + 1):
            output.append(list(seriesintlist[i:i + number:]))
    return output

def largest_product(series, number):
    if number == 0:
        return 1
    total = 0
    for i in slices(series, number):
        z = reduce(lambda x, y: x * y, i, 1)
        if z > total: total = z
    return total
        
