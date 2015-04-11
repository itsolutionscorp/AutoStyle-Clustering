def slices(digits, n):

    if n < 1:
        raise ValueError('Invalid length')

    l = len(digits)
    if n > l:
        raise ValueError('The requested length is longer than the base string')

    dlist =  [int(d) for d in digits]

    s = []
    for i in range(l-n+1):
        s.append(dlist[i:i+n])

    return s
