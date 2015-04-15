def slices(number, n):
    start = 0
    consec = []
    if n > len(number) or n is 0:
        raise ValueError
    else:
        for k, v in enumerate(number):
            if len(number[k:n+k]) is n:
                consec.append([int(n) for n in number[k:n+k]])
        return consec
