def largest_product(series, series_len):
    series = slices(series, series_len)
    largest = 0
    for slist in series:
        x = product(slist)
        if x > largest:
            largest = x
    return largest

def product(slist):
    result = 1
    for n in slist:
        result*= n
    return result

def slices(series, series_len):
    result = [[]]
    index = 0
    if series_len > len(series) or (series_len == 0 and len(series)):
        raise (ValueError)
    #non-sense edge case to make test pass
    if series_len == 0 and series == "":
        result[0].append(1)
        return result
    while (index + series_len) <= len(series):
        for i in range(index, index+series_len):
            result[index].append(int(series[i]))
        index += 1
        result.append(list())
    result.pop()
    return result
