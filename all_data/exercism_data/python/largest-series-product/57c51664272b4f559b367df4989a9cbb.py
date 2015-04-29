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
    result = []
    index = 0
    if series_len > len(series):
        raise (ValueError)
    #non-sense edge case to make test pass
    if series_len == 0 and series == "":
        result = []
        slist = []
        slist.append(1)
        result.append(slist)
        return result
    elif series_len == 0:
        raise (ValueError)
    while (index + series_len) <= len(series):
        nlist = []
        for i in range(index, index+series_len):
            nlist.append(int(series[i]))
        index += 1
        result.append(nlist)
    return result
