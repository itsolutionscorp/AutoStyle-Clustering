def slices(s, num):
    result = []
    if num > len(s) or  num < 1:
        raise ValueError('%d is not a valid length for "%s"' % (num, s))
    arr = [int(x) for x in s]
    for i in range(len(arr) - num + 1):
        result.append(arr[i:i+num])
    return result
