def slices(num, count):
    num = list(map(int, num))
    slices = []
    x = 0
    y = count
    z = 0
    if count == 0 or count > len(num):
        raise ValueError("Count should be greater than numbers lenght.")
    while z != len(num):
        K = num[x:y]
        if len(K) == count:
            slices.append(list(K))
        x += 1
        y += 1
        z += 1
    return slices
