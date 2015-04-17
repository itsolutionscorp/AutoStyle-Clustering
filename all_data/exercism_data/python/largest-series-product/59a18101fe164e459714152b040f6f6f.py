def slices(value, length):
    if length > len(value):
        raise ValueError('There is a problem')
    out, value = [], [int(i) for i in value]
    for i in range((len(value) - length) + 1):
        out.append(value[i:i+length])
    return out

def largest_product(value, length):
    ret = []
    for i in slices(value, length):
        tmp = 1
        for j in i:
            tmp *= j
        ret.append(tmp)
    return max(ret)