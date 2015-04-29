def slices(num, size):
    if size > len(number) or size == 0:
        raise ValueError
    return [[int(n) for n in num[i:i+size]] for i in range(0, len(num)-size+1)]
